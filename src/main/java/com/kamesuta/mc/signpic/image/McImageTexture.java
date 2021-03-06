package com.kamesuta.mc.signpic.image;

import org.apache.commons.lang3.StringUtils;

import com.kamesuta.mc.signpic.Client;
import com.kamesuta.mc.signpic.image.meta.ImageSize;

import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;

public class McImageTexture implements IImageTexture {
	protected static final ResourceLocation Null = Client.mc.renderEngine.getDynamicTextureLocation("null", TextureUtil.missingTexture);
	protected static final ImageSize DefaultSize = new ImageSize().defaultSize();
	protected ResourceLocation location;

	public McImageTexture(final ResourceLocation location) {
		this.location = location;
	}

	@Override
	public void bind() {
		if (!StringUtils.isEmpty(this.location.getResourcePath()))
			Client.mc.renderEngine.bindTexture(this.location);
		else {
			Client.mc.renderEngine.bindTexture(Null);
		}
	}

	@Override
	public ImageSize getSize() {
		return DefaultSize;
	}
}
