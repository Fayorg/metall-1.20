package me.fayorg.monkecraft.metall.api;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class MetallCapabilityProvider implements ICapabilityProvider {

    private final Map<Capability<?>, LazyOptional<?>> capabilities;

    public MetallCapabilityProvider() {
        this(new HashMap<>());
    }
    public MetallCapabilityProvider(Map<Capability<?>, LazyOptional<?>> capabilities) {
        this.capabilities = capabilities;
    }

    public <T> void add(Capability<T> capability, LazyOptional<T> instance) {
        capabilities.putIfAbsent(capability, instance);
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return capabilities.getOrDefault(cap, LazyOptional.empty()).cast();
    }
}
