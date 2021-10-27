package me.darki.konas.module.movement;

import cookiedragon.eventsystem.Subscriber;
import me.darki.konas.Category;
import me.darki.konas.Class19;
import me.darki.konas.Class428;
import me.darki.konas.Class550;
import me.darki.konas.MoveEvent;
import me.darki.konas.module.Module;
import me.darki.konas.setting.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class FastFall
extends Module {
    public static Setting<Boolean> Field1091 = new Setting<>("Accelerate", true);
    public static Setting<Class428> Field1092 = new Setting<>("Horizontal", Class428.CANCEL);
    public static Setting<Float> Field1093 = new Setting<>("Multiplier", Float.valueOf(1.0f), Float.valueOf(50.0f), Float.valueOf(1.0f), Float.valueOf(0.5f));
    public static Setting<Integer> Field1094 = new Setting<>("ShiftTicks", 1, 10, 0, 1);
    public boolean Field1095;
    public boolean Field1096;

    @Subscriber
    public void Method503(MoveEvent moveEvent) {
        block3: {
            if (this.Field1096) {
                return;
            }
            if (!this.Field1095 || FastFall.mc.player.onGround || !(moveEvent.getY() < 0.0)) break block3;
            for (int i = 0; i < (Integer)Field1094.getValue(); ++i) {
                this.Field1096 = true;
                FastFall.mc.player.move(moveEvent.getMoverType(), Field1092.getValue() == Class428.BOOST ? moveEvent.getX() : 0.0, moveEvent.getY(), Field1092.getValue() == Class428.BOOST ? moveEvent.getZ() : 0.0);
                this.Field1096 = false;
                Class550.Method883(FastFall.mc.player.rotationYaw, FastFall.mc.player.rotationPitch);
                if (!((Boolean)Field1091.getValue()).booleanValue()) continue;
                moveEvent.setY(moveEvent.getY() - 0.08);
                FastFall.mc.player.motionY -= 0.08;
            }
            if (Field1092.getValue() == Class428.CANCEL) {
                moveEvent.setX(0.0);
                moveEvent.setZ(0.0);
            }
        }
    }

    public FastFall() {
        super("FastFall", "Makes you fall faster", Category.MOVEMENT, new String[0]);
    }

    @Subscriber
    public void Method130(Class19 class19) {
        if (FastFall.mc.world.getBlockState(new BlockPos((Entity)FastFall.mc.player)).getBlock() != Blocks.AIR) {
            this.Field1095 = false;
            return;
        }
        if (FastFall.mc.player.onGround) {
            FastFall.mc.player.motionY = -0.0784 * (double)((Float)Field1093.getValue()).floatValue();
            this.Field1095 = true;
        }
        if (FastFall.mc.player.motionY > 0.0) {
            this.Field1095 = false;
        }
    }

    @Override
    public void onEnable() {
        this.Field1095 = false;
        this.Field1096 = false;
    }
}