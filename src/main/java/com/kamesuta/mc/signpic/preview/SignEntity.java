package com.kamesuta.mc.signpic.preview;

import com.kamesuta.mc.signpic.Client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class SignEntity {
	private final PreviewTileEntitySign tileSign = new PreviewTileEntitySign(Blocks.standing_sign);
	private boolean renderable = false;

	private PreviewTileEntitySign onItemUse(final EntityPlayer playerIn, final World worldIn, int x, int y, int z, final int direction)
	{
		if (direction == 0)
		{
			return null;
		}
		else if (!worldIn.getBlock(x, y, z).getMaterial().isSolid())
		{
			return null;
		}
		else
		{
			if (direction == 1)
			{
				++y;
			}

			if (direction == 2)
			{
				--z;
			}

			if (direction == 3)
			{
				++z;
			}

			if (direction == 4)
			{
				--x;
			}

			if (direction == 5)
			{
				++x;
			}

			if (!Blocks.standing_sign.canPlaceBlockAt(worldIn, x, y, z))
			{
				return null;
			}
			else
			{
				this.tileSign.xCoord = x;
				this.tileSign.yCoord = y;
				this.tileSign.zCoord = z;

				if (direction == 1)
				{
					this.tileSign.setBlockType(Blocks.standing_sign);
					final int i = MathHelper.floor_double((playerIn.rotationYaw + 180.0F) * 16.0F / 360.0F + 0.5D) & 15;
					this.tileSign.setBlockMetadata(i);
				}
				else
				{
					this.tileSign.setBlockType(Blocks.wall_sign);
					this.tileSign.setBlockMetadata(direction);
				}

				this.renderable = true;

				return this.tileSign;
			}
		}
	}

	public TileEntitySign capturePlace() {
		final Minecraft mc = Client.mc;
		final MovingObjectPosition over = mc.objectMouseOver;
		if (over != null && mc.thePlayer != null) {
			return onItemUse(mc.thePlayer, mc.theWorld, over.blockX, over.blockY, over.blockZ, over.sideHit);
		}
		return null;
	}

	public TileEntitySign getTileEntity() {
		return this.tileSign;
	}

	public TileEntitySign getRenderTileEntity() throws IllegalStateException {
		if (!isRenderable()) throw new IllegalStateException("Not Renderable");
		return this.tileSign;
	}

	public boolean isRenderable() {
		return this.renderable;
	}
}
