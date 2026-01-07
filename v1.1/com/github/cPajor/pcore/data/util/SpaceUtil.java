package com.github.cPajor.pcore.data.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public  class SpaceUtil {
    public static List<Location> getSquare( Location center,  int radius) {
         List<Location> locs = new ArrayList<Location>();
         int cX = center.getBlockX();
         int cZ = center.getBlockZ();
         int minX = Math.min(cX + radius, cX - radius);
         int maxX = Math.max(cX + radius, cX - radius);
         int minZ = Math.min(cZ + radius, cZ - radius);
         int maxZ = Math.max(cZ + radius, cZ - radius);
        for (int x = minX; x <= maxX; ++x) {
            for (int z = minZ; z <= maxZ; ++z) {
                locs.add(new Location(center.getWorld(), (double)x, (double)center.getBlockY(), (double)z));
            }
        }
        locs.add(center);
        return locs;
    }
    
    public static List<Location> getCorners( Location center,  int radius) {
         List<Location> locs = new ArrayList<Location>();
         int cX = center.getBlockX();
         int cZ = center.getBlockZ();
         int minX = Math.min(cX + radius, cX - radius);
         int maxX = Math.max(cX + radius, cX - radius);
         int minZ = Math.min(cZ + radius, cZ - radius);
         int maxZ = Math.max(cZ + radius, cZ - radius);
        locs.add(new Location(center.getWorld(), (double)minX, (double)center.getBlockY(), (double)minZ));
        locs.add(new Location(center.getWorld(), (double)maxX, (double)center.getBlockY(), (double)minZ));
        locs.add(new Location(center.getWorld(), (double)minX, (double)center.getBlockY(), (double)maxZ));
        locs.add(new Location(center.getWorld(), (double)maxX, (double)center.getBlockY(), (double)maxZ));
        return locs;
    }
    
    public static List<Location> getSquare( Location center,  int radius,  int height) {
         List<Location> locs = getSquare(center, radius);
        for (int i = 1; i <= height; ++i) {
            locs.addAll(getSquare(new Location(center.getWorld(), (double)center.getBlockX(), (double)(center.getBlockY() + i), (double)center.getBlockZ()), radius));
        }
        return locs;
    }
    
    public static List<Location> getCorners( Location center,  int radius,  int height) {
         List<Location> locs = getCorners(center, radius);
        for (int i = 1; i <= height; ++i) {
            locs.addAll(getCorners(new Location(center.getWorld(), (double)center.getBlockX(), (double)(center.getBlockY() + i), (double)center.getBlockZ()), radius));
        }
        return locs;
    }
}
