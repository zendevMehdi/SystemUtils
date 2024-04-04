
## Authors

- [@Mehdi Lavasani](https://github.com/zendevMehdi)


## Features

- Access hardware information (cpu, memory, partitions, monitor, keyboard, ...)
- Fetch operating system information
- Fetch user information and access directories
- Access installed jvm information on the system
- Execute new processes in the background easily
## Installation

You can get jar from release section or create new project and add src folder to the project.
    
# SystemUtils

This library help developers to access hardware information and use operating system tools to fetch and view information from system.



## Usage/Examples

Here we access all the partitions mounted on the system and fetch information from them.

```java
package org.zendev.lib.system;

import org.zendev.lib.system.partition.PartitionManager;

public class App {
    public static void main(String[] args) {
        System.out.println("Access system partitions");
        System.out.println("----------------------------------");

        PartitionManager.getPartitions(true).forEach(p -> {
            System.out.printf("%s\n", p.getPartition());
            System.out.printf("Total space: %d (bytes)\n", p.getTotalSpace());
            System.out.printf("Free space: %d (bytes)\n", p.getFreeSpace());
            System.out.printf("Used space: %d (bytes)\n", p.getUsedSpace());
            System.out.printf("System partition: %b\n", p.isSystemPartition());
        });
    }
}
```

