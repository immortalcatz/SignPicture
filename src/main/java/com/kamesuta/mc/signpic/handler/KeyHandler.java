package com.kamesuta.mc.signpic.handler;

import org.lwjgl.input.Keyboard;

import com.kamesuta.mc.signpic.Client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.settings.KeyBinding;

public class KeyHandler {
	public static enum Keys {
		KEY_BINDING_GUI(new KeyBinding("signpic.key.gui", Keyboard.KEY_BACKSLASH, "signpic.key.category")) {
			@Override
			public void onKeyInput(final InputEvent event, final KeyBinding binding) {
				if (Client.mc.currentScreen == null && binding.isPressed()) {
					Client.openEditor();
				}
			}
		},
		;

		public final KeyBinding binding;
		private Keys(final KeyBinding binding) {
			this.binding = binding;
		}

		public abstract void onKeyInput(InputEvent event, KeyBinding binding);
	}

	public static void init() {
		for (final Keys key : Keys.values()) {
			ClientRegistry.registerKeyBinding(key.binding);
		}
	}

	@CoreEvent
	public void onKeyInput(final InputEvent event) {
		for (final Keys key : Keys.values()) {
			key.onKeyInput(event, key.binding);
		}
	}
}
