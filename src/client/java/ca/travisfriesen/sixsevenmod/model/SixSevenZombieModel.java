package ca.travisfriesen.sixsevenmod.model;

import ca.travisfriesen.sixsevenmod.SixSevenMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SixSevenZombieModel<T extends LivingEntityRenderState> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SixSevenMod.MOD_ID, "six_seven_zombie"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart headwear;
    private final ModelPart body;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    public SixSevenZombieModel(ModelPart root) {
        super(root);
        this.root = root;
        this.head = root.getChild("head");
        this.headwear = root.getChild("headwear");
        this.body = root.getChild("body");
        this.leftArm = root.getChild("left_arm");
        this.rightArm = root.getChild("right_arm");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // Head
        partdefinition.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // Headwear
        partdefinition.addOrReplaceChild("headwear",
                CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // Body
        partdefinition.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(16, 16)
                        .addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // Left Arm
        partdefinition.addOrReplaceChild("left_arm",
                CubeListBuilder.create()
                        .texOffs(40, 16)
                        .mirror()
                        .addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                        .mirror(false),
                PartPose.offset(5.0F, 2.0F, 0.0F));

        // Right Arm
        partdefinition.addOrReplaceChild("right_arm",
                CubeListBuilder.create()
                        .texOffs(40, 16)
                        .addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-5.0F, 2.0F, 0.0F));

        // Left Leg
        partdefinition.addOrReplaceChild("left_leg",
                CubeListBuilder.create()
                        .texOffs(0, 16)
                        .mirror()
                        .addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                        .mirror(false),
                PartPose.offset(1.9F, 12.0F, 0.0F));

        // Right Leg
        partdefinition.addOrReplaceChild("right_leg",
                CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T renderState) {
        // Head rotation
        this.head.yRot = renderState.yRot * ((float)Math.PI / 180F);
        this.head.xRot = renderState.xRot * ((float)Math.PI / 180F);
        this.headwear.yRot = this.head.yRot;
        this.headwear.xRot = this.head.xRot;

        // Get the age in ticks for continuous animation
        float ageInTicks = renderState.ageInTicks;

        // 6-7 arm animation - plays continuously
        // 1.5 second loop (30 ticks at 20 tps)
        float animationProgress = (ageInTicks % 30.0F) / 30.0F;

        // Convert degrees to radians for the arm swing animation
        // Left arm: -70 to -120 to -70 to -120
        // Right arm: -120 to -70 to -120 to -70 (opposite)
        float leftArmRotation;
        float rightArmRotation;

        if (animationProgress < 0.333F) {
            // First third: left -70 to -120, right -120 to -70
            float progress = animationProgress / 0.333F;
            leftArmRotation = Mth.lerp(progress, -70, -120);
            rightArmRotation = Mth.lerp(progress, -120, -70);
        } else if (animationProgress < 0.666F) {
            // Second third: left -120 to -70, right -70 to -120
            float progress = (animationProgress - 0.333F) / 0.333F;
            leftArmRotation = Mth.lerp(progress, -120, -70);
            rightArmRotation = Mth.lerp(progress, -70, -120);
        } else {
            // Final third: left -70 to -120, right -120 to -70
            float progress = (animationProgress - 0.666F) / 0.334F;
            leftArmRotation = Mth.lerp(progress, -70, -120);
            rightArmRotation = Mth.lerp(progress, -120, -70);
        }

        this.leftArm.xRot = leftArmRotation * ((float)Math.PI / 180F);
        this.rightArm.xRot = rightArmRotation * ((float)Math.PI / 180F);

        // Walking animation for legs
        float limbSwing = renderState.walkAnimationPos;
        float limbSwingAmount = renderState.walkAnimationSpeed;

        this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
    }
}

