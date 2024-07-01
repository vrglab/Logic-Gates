package org.Vrglab.LogicGates.World;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.Vrglab.LogicGates.LogicGatesMod;
import org.Vrglab.Modloader.Registration.Registry;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class WorldUtils {

    public static ResourceLocation createLocation(String obj) {
        return new ResourceLocation(LogicGatesMod.MOD_ID, obj);
    }

    /**
     * Creates an instance of a class using reflection, with the given constructor arguments.
     *
     * @param clazz           Class object of the class to instantiate.
     * @param constructorArgs Arguments to pass to the constructor.
     * @return An instance of the class.
     */
    public static Object createInstance(Class<?> clazz, Object... constructorArgs) {
        try {
            Class<?>[] argTypes = new Class<?>[constructorArgs.length];
            for (int i = 0; i < constructorArgs.length; i++) {
                if (constructorArgs[i] != null) {
                    argTypes[i] = constructorArgs[i].getClass();
                } else {
                    throw new IllegalArgumentException("Constructor argument cannot be null.");
                }
            }

            // Find the constructor that matches the argument types or their subclasses
            Constructor<?>[] constructors = clazz.getConstructors();
            Constructor<?> foundConstructor = null;

            outerLoop:
            for (Constructor<?> constructor : constructors) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                if (parameterTypes.length == argTypes.length) {
                    for (int i = 0; i < parameterTypes.length; i++) {
                        if (!parameterTypes[i].isAssignableFrom(argTypes[i])) {
                            continue outerLoop; // Skip to the next constructor
                        }
                    }
                    foundConstructor = constructor;
                    break;
                }
            }

            if (foundConstructor == null) {
                throw new NoSuchMethodException("No constructor found with matching parameter types.");
            }

            return foundConstructor.newInstance(constructorArgs);
        } catch (Throwable t){
            return null;
        }
    }

    public static Item.Properties getBaseSettings(){
        return new Item.Properties();
    }
    public static Item.Properties getBaseSettings(ResourceKey<CreativeModeTab> tab){
        return new Item.Properties().arch$tab(tab);
    }


    @Nullable
    public static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> checkType(BlockEntityType<A> givenType, BlockEntityType<E> expectedType, BlockEntityTicker<? super E> ticker) {
        return expectedType == givenType ? (BlockEntityTicker<A>) ticker : null;
    }


    /** BLOCK CREATION HELPER **/

    public static <T extends Block> Object createBlock(String name, Supplier<Item.Properties> block_item_properties , Class<T> clazz, Object... block_args){
        return Registry.RegisterBlock(name, LogicGatesMod.MOD_ID, ()->createInstance(clazz, block_args), block_item_properties);
    }

    public static <T extends Block> Object createBlock(String name, Supplier<Item.Properties> settings, BlockBehaviour.Properties properties, Class<T> clazz){
        return createBlock(name, settings, clazz, properties);
    }

    public static Object createBlock(String name, Supplier<Item.Properties> settings, BlockBehaviour.Properties properties){
        return createBlock(name, settings, properties, Block.class);
    }


    /** INGOT CREATION HELPER **/

    public static <T extends Item> Object createItem(String name, Class<T> clazz, Object... item_args){
        return Registry.RegisterItem(name, LogicGatesMod.MOD_ID,
                ()->createInstance(clazz, item_args));
    }

    public static <T extends Item> Object createItem(String name, Item.Properties settings, Class<T> clazz){
        return createItem(name, clazz, settings);
    }

    public static Object createItem(String name, Item.Properties settings){
        return createItem(name, settings, Item.class);
    }


    /** TOOL SET CREATION HELPERS **/

    public static Map<ResourceLocation, Object> createToolSet(String name, Tier material, int[] attack_damages, float[] attack_speeds, Item.Properties[] item_properties) {
        Map<ResourceLocation, Object> map = new HashMap<>();
        map.put(createLocation(name.toLowerCase()+ "_sword"), Registry.RegisterItem(name.toLowerCase() + "_sword", LogicGatesMod.MOD_ID,
                ()->new SwordItem(material, attack_damages[0], attack_speeds[0], item_properties[0])));

        map.put(createLocation(name.toLowerCase() + "_pickaxe"),Registry.RegisterItem(name.toLowerCase() + "_pickaxe", LogicGatesMod.MOD_ID,
                ()->new PickaxeItem(material, attack_damages[1], attack_speeds[1], item_properties[1])));

        map.put(createLocation(name.toLowerCase() + "_axe"),  Registry.RegisterItem(name.toLowerCase() + "_axe", LogicGatesMod.MOD_ID,
                ()->new AxeItem(material, attack_damages[2], attack_speeds[2], item_properties[2])));

        map.put(createLocation(name.toLowerCase() + "_shovel"),    Registry.RegisterItem(name.toLowerCase() + "_shovel", LogicGatesMod.MOD_ID,
                ()->new ShovelItem(material, attack_damages[3], attack_speeds[3], item_properties[3])));

        map.put(createLocation(name.toLowerCase() + "_hoe"), Registry.RegisterItem(name.toLowerCase() + "_hoe", LogicGatesMod.MOD_ID,
                ()->new HoeItem(material, attack_damages[4], attack_speeds[4], item_properties[4])));

        return map;
    }

    public static Map<ResourceLocation, Object> createToolSet(String name, Tier material, int[] attack_damages, float[] attack_speeds, Item.Properties item_properties) {
        return createToolSet(name, material, attack_damages, attack_speeds, new Item.Properties[]{item_properties, item_properties, item_properties, item_properties,item_properties});
    }


    /** ARMOR CREATION HELPERS **/

    public static <T extends ArmorItem> Map<ResourceLocation, Object> createArmorSet(String name, ArmorMaterial material, Class<T> armor_class, Item.Properties[] item_properties) {
        Map<ResourceLocation, Object> map = new HashMap<>();

        var obj = Registry.RegisterItem(name.toLowerCase()+ "_helmet", LogicGatesMod.MOD_ID,
                ()->createInstance(armor_class, material, ArmorItem.Type.HELMET, item_properties[0]));


        map.put(createLocation(name.toLowerCase()+ "_helmet"), obj);

        map.put(createLocation(name.toLowerCase()+ "_chestplate"), Registry.RegisterItem(name.toLowerCase()+ "_chestplate", LogicGatesMod.MOD_ID,
                ()->createInstance(armor_class, material, ArmorItem.Type.CHESTPLATE, item_properties[1])));

        map.put(createLocation(name.toLowerCase()+ "_leggings"), Registry.RegisterItem(name.toLowerCase()+ "_leggings", LogicGatesMod.MOD_ID,
                ()->createInstance(armor_class, material, ArmorItem.Type.LEGGINGS, item_properties[2])));

        map.put(createLocation(name.toLowerCase()+ "_boots"), Registry.RegisterItem(name.toLowerCase()+ "_boots", LogicGatesMod.MOD_ID,
                ()->createInstance(armor_class, material, ArmorItem.Type.BOOTS, item_properties[3])));
        return map;
    }

    public static  <T extends ArmorItem> Map<ResourceLocation, Object> createArmorSet(String name, ArmorMaterial material, Class<T> armor_class, Item.Properties item_properties) {
        return createArmorSet(name, material, armor_class, new Item.Properties[]{item_properties, item_properties, item_properties, item_properties,item_properties});
    }

    /** get all mapping helper **/

    public static void mapItems(Map map, List<Object> obj_list){
        for (var obj:map.values()) {
            obj_list.add(obj);
        }
    }

    public static void mapSet(Map item_map, Map armor_map,List<Object> obj_list){
        mapItems(item_map, obj_list);
        mapItems(armor_map, obj_list);
    }
}
