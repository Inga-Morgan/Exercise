package com.display.fitness.bean;


import com.display.fitness.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ye's
 * @date :   2021/3/15
 * @desc :
 */
public class DataFactory {

    public static List<HomeMenuItem> loadData() {
        List<HomeMenuItem> items = new ArrayList<>();
        items.add(new HomeMenuItem(R.mipmap.ic_nav_bill, "健走圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172730_iSTcz.png"));
        items.add(new HomeMenuItem(R.mipmap.ic_nav_device_binding, "跑步圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172629_kE4jY.png"));
        items.add(new HomeMenuItem(R.mipmap.ic_nav_member_manage, "游泳圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172705_2yXk8.png"));
        items.add(new HomeMenuItem(R.mipmap.ic_nav_msg, "打球圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172705_irvud.png"));
        items.add(new HomeMenuItem(R.mipmap.ic_nav_download, "跳舞圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172629_PcmPU.png"));
        items.add(new HomeMenuItem(R.mipmap.ic_nav_excel, "有氧操圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172629_NeFKy.png"));
        items.add(new HomeMenuItem(R.mipmap.ic_nav_favor, "太极圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172629_yUFai.png"));
        items.add(new HomeMenuItem(R.mipmap.ic_nav_goods, "瑜伽圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172629_KahtV.png"));
        items.add(new HomeMenuItem(R.mipmap.ic_nav_group, "爬山圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172705_vuZsr.png"));
        items.add(new HomeMenuItem(R.mipmap.ic_nav_devices, "拳击圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172629_t4sRT.png"));
        items.add(new HomeMenuItem(R.mipmap.ic_nav_vip_card, "跳绳圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172730_j2td3.png"));
        items.add(new HomeMenuItem(R.mipmap.ic_nav_vip_integral, "深蹲圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172730_rArtS.png"));
        items.add(new HomeMenuItem(R.mipmap.ic_nav_members, "箭步蹲圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172705_LsMFQ.png"));
        items.add(new HomeMenuItem(R.mipmap.ic_nav_device_magage, "引体向上圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172629_UcuEH.png"));
        items.add(new HomeMenuItem(R.mipmap.ic_nav_shop_decorate, "卧推圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172705_fleMs.png"));
        items.add(new HomeMenuItem(R.mipmap.ic_nav_order, "俯卧撑圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172705_zzyP2.png"));
        items.add(new HomeMenuItem(R.mipmap.ic_nav_service, "平板支撑圈", "https://c-ssl.duitang.com/uploads/item/201911/25/20191125172705_Wuun5.png"));
       return items;
    }

    public static List<HomeMenuItem> loadOriginData(List<GroupIconsBean.CircleIcons> list) {
        List<HomeMenuItem> items = new ArrayList<>();
        for (int i =0 ; i < list.size(); i++) {
            items.add(new HomeMenuItem(list.get(i).getName(),list.get(i).getIcon()));
        }
        return items;
    }


}