## Android 概述

### Activity

activit的生命周期包括不存在，停止，暂停，运行四种。如下图所示：
![生命周期](./img/activity_lifecycle.png)

注意事项：
1. 在设备旋转时，系统会销毁当前activity，然后创建一个新的activity。
2. activity只有在暂停或者停止状态下才可能被销毁，Android不会为了回收内存，而去销毁正在运行的activity。