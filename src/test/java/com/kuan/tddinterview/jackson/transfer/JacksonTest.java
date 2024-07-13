package com.kuan.tddinterview.jackson.transfer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JacksonTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void should_get_entity_from_json() throws JsonProcessingException {
        Root root = objectMapper.readValue(retJson, Root.class);
        Assertions.assertNotNull(root);
    }

    @Test
    public void should_get_data_of_sub_property_entity_from_json() throws JsonProcessingException {
        Root root = objectMapper.readValue(retJson, Root.class);
        Assertions.assertNotNull(root.getData());
    }

    @Test
    public void should_get_list_of_sub_property_data_entity_from_json() throws JsonProcessingException {
        Root root = objectMapper.readValue(retJson, Root.class);
        Assertions.assertNotNull(root.getData().getList());
    }



    @Test
    public void should_get_specific_value_from_json() throws JsonProcessingException {
        Root root = objectMapper.readValue(retJson, Root.class);
        List<Profile> list = root.getData().getList();
        Assertions.assertEquals("abc", list.get(0).getHeadBox().getDesc());
    }



    String retJson = """
            {
                "code": 0,
                "message": "0",
                "ttl": 1,
                "data": {
                    "list": [
                        {
                            "head_box": {
                                "name": "钢之勇者",
                                "value": "https://i0.hdslb.com/bfs/live/6f205e70b5f7135a5401b1fe694f3cb6ecdedd69.png",
                                "desc": "abc"
                            },
                            "area_v2_id": 530,
                            "area_v2_parent_id": 1,
                            "area_v2_name": "萌宅领域",
                            "area_v2_parent_name": "娱乐",
                            "broadcast_type": 1,
                            "cover": "http://i0.hdslb.com/bfs/live/user_cover/3ac0a78092e6977f567a7f9365f1bd3adc7fc1b3.jpg",
                            "link": "/32568095?hotRank=0",
                            "online": 5778,
                            "pendant_Info": {},
                            "roomid": 32568095,
                            "title": "考完试找不到……",
                            "uname": "期音_",
                            "face": "https://i1.hdslb.com/bfs/face/c2b23137e6cad4e91311a52d38083504bbbab37e.jpg",
                            "verify": {
                                "role": 0,
                                "desc": "",
                                "type": -1
                            },
                            "uid": 3546668205082945,
                            "keyframe": "http://i0.hdslb.com/bfs/live-key-frame/keyframe0713094100003256809527fisj.jpg",
                            "is_auto_play": 0,
                            "head_box_type": 1,
                            "flag": 0,
                            "session_id": "2cc6115505ce10796a5dca76606691db_C5146BA5-A4B5-485C-9BB3-B94A4A428545",
                            "group_id": 1000221,
                            "show_callback": "https://live-trace.bilibili.com/xlive/data-interface/v1/index/log?sessionID=2cc6115505ce10796a5dca76606691db_C5146BA5-A4B5-485C-9BB3-B94A4A428545\\u0026group_id=1000221\\u0026biz=live\\u0026event_id=live_card_show\\u0026rule_key=\\u0026special_id=0\\u0026roomid=32568095\\u0026parent_id=1\\u0026area_id=530\\u0026page=0\\u0026position=1\\u0026platform=web",
                            "click_callback": "https://live-trace.bilibili.com/xlive/data-interface/v1/index/log?sessionID=2cc6115505ce10796a5dca76606691db_C5146BA5-A4B5-485C-9BB3-B94A4A428545\\u0026group_id=1000221\\u0026biz=live\\u0026event_id=live_card_click\\u0026rule_key=\\u0026special_id=0\\u0026roomid=32568095\\u0026parent_id=1\\u0026area_id=530\\u0026page=0\\u0026position=1\\u0026platform=web",
                            "special_id": 0,
                            "watched_show": {
                                "switch": true,
                                "num": 232,
                                "text_small": "232",
                                "text_large": "232人看过",
                                "icon": "https://i0.hdslb.com/bfs/live/a725a9e61242ef44d764ac911691a7ce07f36c1d.png",
                                "icon_location": 0,
                                "icon_web": "https://i0.hdslb.com/bfs/live/8d9d0f33ef8bf6f308742752d13dd0df731df19c.png"
                            },
                            "is_nft": 0,
                            "nft_dmark": "",
                            "is_ad": false,
                            "ad_transparent_content": null,
                            "show_ad_icon": false,
                            "status": false,
                            "followers": 0
                        }
                    ]
                }
            }
            """;


}
