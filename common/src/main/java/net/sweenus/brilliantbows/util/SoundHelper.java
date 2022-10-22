package net.sweenus.brilliantbows.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import net.sweenus.brilliantbows.BrilliantBows;

public class SoundHelper {


    static SoundEvent sound;
    static World world;
    static PositionedSoundInstance SOUND;

    public void SoundSet(World world, PositionedSoundInstance SOUND) {
        this.world = world;
        this.SOUND = SOUND;
    }


    public void playSound() {
        MinecraftClient.getInstance().getSoundManager().play(SOUND);
        //System.out.println("We have tried to play the sound");
    }

    public void stopSound() {
        MinecraftClient.getInstance().getSoundManager().stop(SOUND);
        //System.out.println("We have tried to stop the sound");
    }

}
