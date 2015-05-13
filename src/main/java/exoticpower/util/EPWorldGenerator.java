package exoticpower.util;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;
import exoticpower.core.EPBlocks;

public class EPWorldGenerator implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    	
    	switch (world.provider.dimensionId) {
        case 0:
        	GenerateOverworld(EPBlocks.copper_ore,random,chunkX*16,chunkZ*16, world);
        	GenerateOverworld(EPBlocks.tin_ore,random,chunkX*16,chunkZ*16, world);
        	break;
        case 1:
        	GenerateOverEnd(EPBlocks.end_ore,random,chunkX*16,chunkZ*16, world);
        	addOreSpawnEnd(EPBlocks.chrysocolla_ore, world, random, chunkX*16,chunkZ*16, 5, 10, 2, 0, 70);
        	addOreSpawnEnd(EPBlocks.sapphire_ore, world, random, chunkX*16,chunkZ*16, 1, 5, 2, 0, 70);
        	break;
        case -1:
        	GenerateOverNether(EPBlocks.nether_ore,random,chunkX*16,chunkZ*16, world);
        	addOreSpawnNether(EPBlocks.agate_ore, world, random, chunkX*16,chunkZ*16, 1, 5, 2, 0, 70);
        	GenerateOverNether(EPBlocks.fire_ore,random,chunkX*16,chunkZ*16, world);
        	break;
}
 
    }
    public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int minVeinSize, int maxVeinSize, int chancesToSpawn, int minY, int maxY )
    {
        WorldGenMinable minable = new WorldGenMinable(block, (minVeinSize + random.nextInt(maxVeinSize - minVeinSize)));
        for(int i = 0; i < chancesToSpawn; i++)
        {
            int posX = blockXPos + random.nextInt(16);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZPos + random.nextInt(16);
            minable.generate(world, random, posX, posY, posZ);
        }
    }
    public void addOreSpawnNether(Block block, World world, Random random, int blockXPos, int blockZPos, int minVeinSize, int maxVeinSize, int chancesToSpawn, int minY, int maxY )
    {
        WorldGenMinable minable = new WorldGenMinable(block, (minVeinSize + random.nextInt(maxVeinSize - minVeinSize)), Blocks.netherrack);
        for(int i = 0; i < chancesToSpawn; i++)
        {
            int posX = blockXPos + random.nextInt(16);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZPos + random.nextInt(16);
            minable.generate(world, random, posX, posY, posZ);
        }
    }
    
    public void addOreSpawnEnd(Block block, World world, Random random, int blockXPos, int blockZPos, int minVeinSize, int maxVeinSize, int chancesToSpawn, int minY, int maxY )
    {
        WorldGenMinable minable = new WorldGenMinable(block, (minVeinSize + random.nextInt(maxVeinSize - minVeinSize)), Blocks.end_stone);
        for(int i = 0; i < chancesToSpawn; i++)
        {
            int posX = blockXPos + random.nextInt(16);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZPos + random.nextInt(16);
            minable.generate(world, random, posX, posY, posZ);
        }
    }
    
    private void GenerateOverworld(Block b,Random random, int x, int z, World world) {

    	this.addOreSpawn(b, world, random, x, z, 12, 24, 5, 0, 70);
    	
     }
    private void GenerateOverNether(Block b,Random random, int x, int z, World world) {

    	this.addOreSpawnNether(b, world, random, x, z, 12, 24, 5, 0, 100);
    	
     }
    private void GenerateOverEnd(Block b,Random random, int x, int z, World world) {

    	this.addOreSpawnEnd(b, world, random, x, z, 10, 12, 5, 0, 70);
    	
     }
}
