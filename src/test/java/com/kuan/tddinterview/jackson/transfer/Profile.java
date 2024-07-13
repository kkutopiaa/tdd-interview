package com.kuan.tddinterview.jackson.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Profile {

    @JsonProperty("head_box")
    private HeadBox headBox;
    @JsonProperty("area_v2_id")
    private int areaV2Id;
    @JsonProperty("area_v2_parent_id")
    private int areaV2ParentId;
    @JsonProperty("area_v2_name")
    private String areaV2Name;
    @JsonProperty("area_v2_parent_name")
    private String areaV2ParentName;
    @JsonProperty("broadcast_type")
    private int broadcastType;
    private String cover;
    private String link;
    private int online;

    private int roomid;
    private String title;
    private String uname;
    private String face;

    private long uid;
    private String keyframe;
    @JsonProperty("is_auto_play")
    private int isAutoPlay;
    @JsonProperty("head_box_type")
    private int headBoxType;
    private int flag;
    @JsonProperty("session_id")
    private String sessionId;
    @JsonProperty("group_id")
    private int groupId;
    @JsonProperty("show_callback")
    private String showCallback;
    @JsonProperty("click_callback")
    private String clickCallback;
    @JsonProperty("special_id")
    private int specialId;

    @JsonProperty("is_nft")
    private int isNft;
    @JsonProperty("nft_dmark")
    private String nftDmark;
    @JsonProperty("is_ad")
    private boolean isAd;
    @JsonProperty("ad_transparent_content")
    private String adTransparentContent;
    @JsonProperty("show_ad_icon")
    private boolean showAdIcon;
    private boolean status;
    private int followers;


}
