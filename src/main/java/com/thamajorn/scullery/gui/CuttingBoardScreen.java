package com.thamajorn.scullery.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.thamajorn.scullery.container.CuttingBoardContainer;
import com.thamajorn.scullery.scullery;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class CuttingBoardScreen extends ContainerScreen<CuttingBoardContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(scullery.MOD_ID, "textures/gui/cuttingboardbackground.png");

    public CuttingBoardScreen(CuttingBoardContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);

        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 175;
        this.ySize = 165;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        CuttingBoardScreen.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize, 299, 299);

        this.blit(matrixStack, this.guiLeft + 67, this.guiTop + 25, 176, 0, this.container.getProgressionScale(),35);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        this.font.drawString(matrixStack, this.title.getString(), 5.0f, 5.0f, 0x404040);

    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }
}
