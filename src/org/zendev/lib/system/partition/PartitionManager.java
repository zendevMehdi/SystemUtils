package org.zendev.lib.system.partition;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PartitionManager {

    public static List<Partition> getPartitions(boolean mounted) {
        List<Partition> partitions = new ArrayList<>();

        for (File p : File.listRoots()) {
            if (mounted) {
                if (p.canWrite()) {
                    partitions.add(new Partition(p.getAbsolutePath()));
                }
            } else {
                partitions.add(new Partition(p.getAbsolutePath()));
            }
        }

        return partitions;
    }
}
