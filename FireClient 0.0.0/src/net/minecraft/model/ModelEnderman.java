// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.model;


// Referenced classes of package net.minecraft.src:
//            ModelBiped, ModelRenderer

public class ModelEnderman extends ModelBiped
{

    public boolean isCarrying;
    public boolean isAttacking;

    public ModelEnderman()
    {
        super(0.0F, -14F);
        isCarrying = false;
        isAttacking = false;
        float f = -14F;
        float f1 = 0.0F;
        hat = new ModelRenderer(this, 0, 16);
        hat.addBox(-4F, -8F, -4F, 8, 8, 8, f1 - 0.5F);
        hat.setRotationPoint(0.0F, 0.0F + f, 0.0F);
        body = new ModelRenderer(this, 32, 16);
        body.addBox(-4F, 0.0F, -2F, 8, 12, 4, f1);
        body.setRotationPoint(0.0F, 0.0F + f, 0.0F);
        rightArm = new ModelRenderer(this, 56, 0);
        rightArm.addBox(-1F, -2F, -1F, 2, 30, 2, f1);
        rightArm.setRotationPoint(-3F, 2.0F + f, 0.0F);
        leftArm = new ModelRenderer(this, 56, 0);
        leftArm.mirror = true;
        leftArm.addBox(-1F, -2F, -1F, 2, 30, 2, f1);
        leftArm.setRotationPoint(5F, 2.0F + f, 0.0F);
        rightLeg = new ModelRenderer(this, 56, 0);
        rightLeg.addBox(-1F, 0.0F, -1F, 2, 30, 2, f1);
        rightLeg.setRotationPoint(-2F, 12F + f, 0.0F);
        leftLeg = new ModelRenderer(this, 56, 0);
        leftLeg.mirror = true;
        leftLeg.addBox(-1F, 0.0F, -1F, 2, 30, 2, f1);
        leftLeg.setRotationPoint(2.0F, 12F + f, 0.0F);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5);
        head.showModel = true;
        float f6 = -14F;
        body.rotateAngleX = 0.0F;
        body.rotationPointY = f6;
        body.rotationPointZ = -0F;
        rightLeg.rotateAngleX -= 0.0F;
        leftLeg.rotateAngleX -= 0.0F;
        rightArm.rotateAngleX *= 0.5D;
        leftArm.rotateAngleX *= 0.5D;
        rightLeg.rotateAngleX *= 0.5D;
        leftLeg.rotateAngleX *= 0.5D;
        float f7 = 0.4F;
        if(rightArm.rotateAngleX > f7)
        {
            rightArm.rotateAngleX = f7;
        }
        if(leftArm.rotateAngleX > f7)
        {
            leftArm.rotateAngleX = f7;
        }
        if(rightArm.rotateAngleX < -f7)
        {
            rightArm.rotateAngleX = -f7;
        }
        if(leftArm.rotateAngleX < -f7)
        {
            leftArm.rotateAngleX = -f7;
        }
        if(rightLeg.rotateAngleX > f7)
        {
            rightLeg.rotateAngleX = f7;
        }
        if(leftLeg.rotateAngleX > f7)
        {
            leftLeg.rotateAngleX = f7;
        }
        if(rightLeg.rotateAngleX < -f7)
        {
            rightLeg.rotateAngleX = -f7;
        }
        if(leftLeg.rotateAngleX < -f7)
        {
            leftLeg.rotateAngleX = -f7;
        }
        if(isCarrying)
        {
            rightArm.rotateAngleX = -0.5F;
            leftArm.rotateAngleX = -0.5F;
            rightArm.rotateAngleZ = 0.05F;
            leftArm.rotateAngleZ = -0.05F;
        }
        rightArm.rotationPointZ = 0.0F;
        leftArm.rotationPointZ = 0.0F;
        rightLeg.rotationPointZ = 0.0F;
        leftLeg.rotationPointZ = 0.0F;
        rightLeg.rotationPointY = 9F + f6;
        leftLeg.rotationPointY = 9F + f6;
        head.rotationPointZ = -0F;
        head.rotationPointY = f6 + 1.0F;
        hat.rotationPointX = head.rotationPointX;
        hat.rotationPointY = head.rotationPointY;
        hat.rotationPointZ = head.rotationPointZ;
        hat.rotateAngleX = head.rotateAngleX;
        hat.rotateAngleY = head.rotateAngleY;
        hat.rotateAngleZ = head.rotateAngleZ;
        if(isAttacking)
        {
            float f8 = 1.0F;
            head.rotationPointY -= f8 * 5F;
        }
    }
}
