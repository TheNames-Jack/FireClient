// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.model;


// Referenced classes of package net.minecraft.src:
//            ModelZombie, ModelRenderer

public class ModelSkeleton extends ModelZombie
{

    public ModelSkeleton()
    {
        float f = 0.0F;
        rightArm = new ModelRenderer(this, 40, 16);
        rightArm.addBox(-1F, -2F, -1F, 2, 12, 2, f);
        rightArm.setRotationPoint(-5F, 2.0F, 0.0F);
        leftArm = new ModelRenderer(this, 40, 16);
        leftArm.mirror = true;
        leftArm.addBox(-1F, -2F, -1F, 2, 12, 2, f);
        leftArm.setRotationPoint(5F, 2.0F, 0.0F);
        rightLeg = new ModelRenderer(this, 0, 16);
        rightLeg.addBox(-1F, 0.0F, -1F, 2, 12, 2, f);
        rightLeg.setRotationPoint(-2F, 12F, 0.0F);
        leftLeg = new ModelRenderer(this, 0, 16);
        leftLeg.mirror = true;
        leftLeg.addBox(-1F, 0.0F, -1F, 2, 12, 2, f);
        leftLeg.setRotationPoint(2.0F, 12F, 0.0F);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        aimedBow = true;
        super.setRotationAngles(f, f1, f2, f3, f4, f5);
    }
}
