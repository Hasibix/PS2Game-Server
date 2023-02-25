package net.hasibix.ps2game.server.utils;

import com.harium.supabase.SupabaseClient;

public class Supabase {
    private static SupabaseClient supabase;

    private Supabase() {}

    public static void Initialize(String supabaseUrl, String supabaseApiKey) {
        supabase = new SupabaseClient(supabaseUrl, supabaseApiKey);
    }

    public static SupabaseClient client() {
        return supabase;
    }
}
