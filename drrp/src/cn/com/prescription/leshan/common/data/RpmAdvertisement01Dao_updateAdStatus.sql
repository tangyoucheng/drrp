/*
 * Copyright(c) 2016 NTT DATA KANSAI CORPORATION
 */
/**
 * 推广信息表DAOIF ソート処理
 * RpmAdvertisementDao_selectBySort.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */

UPDATE
rpm_advertisement
SET ad_status = '2'
WHERE
    ad_status = '1'
    /*IF condition.adId != null && condition.adId != "" */
        AND ad_id <> /*condition.adId*/
    /*END*/

