package com.alysaa.geyserupdater.common.util;
import java.io.*;
import java.lang.management.ManagementFactory;


public class MakeScript {
    public static void createScript(String jarPath) throws IOException {
        File file;
        String extension;
        if (OSUtils.isWindows()) {
            extension = "bat";
        } else if (OSUtils.isLinux() || OSUtils.isMac()) {
            extension = "sh";
        } else {
            System.out.println("Your OS is not supported! We support Linux, Mac, and Windows for automatic script creation!");
            return;
        }
        file = new File("ServerRestartScript." + extension);
        if (!file.exists()) {
            System.out.println("[GeyserUpdater] A custom restart script has been made for you.");
            System.out.println("[GeyserUpdater] You will need to shutdown the server and use our provided restart script.");
            FileOutputStream fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            if (OSUtils.isWindows()) {
                dos.writeBytes("@echo off\n");
            } else if (OSUtils.isLinux() || OSUtils.isMac()) {
                dos.writeBytes("#!/bin/sh\n");
            }
            dos.writeBytes(":restart\n");
            dos.writeBytes("java -Xmx" + ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax() / (1024 * 1024) + "M -jar " + jarPath + " nogui\n");
            dos.writeBytes("Goto restart\n");
        }
    }
}