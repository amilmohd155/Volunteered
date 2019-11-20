package com.volunteerx.app.utils;

import java.io.File;
import java.util.ArrayList;

public class FileSearch {

    /**
     * Search a directory and return a list of **directories** contained inside
     * @param directory
     * @return
     */
    public static ArrayList<String> getDirectoryPaths(String directory) {
        ArrayList<String> pathArray = new ArrayList<>();
        File file = new File(directory);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; ++i) {
            if (files[i].isDirectory()) {
                pathArray.add(files[i].getAbsolutePath());
            }
        }

        return pathArray;
    }

    /**
     * Search a directory and return a list of **files** contained inside
     * @param directory
     * @return pathArray
     */
    public static ArrayList<String> getFilePaths(String directory) {
        ArrayList<String> pathArray = new ArrayList<>();
        File file = new File(directory);
        File[] files = file.listFiles();
        for (int i  = 0;i < files.length; ++i) {
            if (files[i].isFile()) {
                pathArray.add(files[i].getAbsolutePath());
            }
        }

        return pathArray;
    }

}
