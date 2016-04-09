package com.aspros.enjoyword;

import java.util.List;

/**
 * Created by Aspros on 16/4/7.
 */
public class DataUtil {
    //获取库存数
    public static int getAllNum(List<SkuItem> list) {
        int num = 0;
        for (SkuItem item : list) {
            num += item.getNameNum();
        }
        return num;
    }

    //根据版本及类型获取库存
    public static int getNumByVersionAndType(List<SkuItem> list, String versionStr, String typeStr) {
        int num = 0;
        for (SkuItem item : list) {
            if (versionStr.equals(item.getNameVersion()) && typeStr.equals(item.getNameType())) {
                num = item.getNameNum();
                break;
            }
        }
        return num;
    }

    //清空状态
    private static List clearState(List<Bean> list) {
        for (int i = 0; i < list.size(); i++) {
            Bean b = list.get(i);
            b.setStates("0");
            list.set(i, b);
        }
        return list;
    }
    //获取版本所有库存
    public static int getNumByVersion(List<SkuItem> list, String versionStr) {
        int num = 0;
        for (SkuItem item : list) {
            if (item.getNameVersion().equals(versionStr)) {
                num += item.getNameNum();
            }
        }
        return num;
    }
    //获取类型所有库存
    public static int getNumByType(List<SkuItem> list, String typeStr) {
        int num = 0;
        for (SkuItem item : list) {
            if (item.getNameType().equals(typeStr)) {
                num += item.getNameNum();
            }
        }
        return num;
    }
}
