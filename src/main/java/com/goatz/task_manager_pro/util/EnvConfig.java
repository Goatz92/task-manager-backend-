package com.goatz.task_manager_pro.util;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getJwtSecret() {
        return dotenv.get("JWT_SECRET");
    }
}
