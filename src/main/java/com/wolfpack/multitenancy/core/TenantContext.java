package com.wolfpack.multitenancy.core;

public final class TenantContext {
    private static final ThreadLocal<Long> CURRENT = new ThreadLocal<>();
    public static void set(Long id){ CURRENT.set(id); }
    public static Long get(){ return CURRENT.get(); }
    public static void clear(){ CURRENT.remove(); }
    private TenantContext(){}
}
