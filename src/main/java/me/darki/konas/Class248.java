package me.darki.konas;

import cookiedragon.eventsystem.Subscriber;
import me.darki.konas.module.Module;

public class Class248
extends Module {
    public Class248() {
        super("LiquidInteract", Category.EXPLOIT, "LiquidPlace");
    }

    @Subscriber
    public void Method2010(Class655 class655) {
        class655.Cancel();
    }
}