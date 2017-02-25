/*
 * 
 */
/**
 * 处方信息表DAOIF ソート処理
 * RPA00109Dao_selectPrescriptionCount.sql
 */
/*
 * 新規作成
 * DATE: 2012.02.13 NAME: gl
 */
select prescriptionJanInfo.prescriptionCount as janCount,
    prescriptionFebInfo.prescriptionCount as febCount,
    prescriptionMarInfo.prescriptionCount as marCount,
    prescriptionFirstQuarterInfo.prescriptionCount as firstQuarterCount,
    prescriptionAprInfo.prescriptionCount as aprCount,
    prescriptionMayInfo.prescriptionCount as mayCount,
    prescriptionJuneInfo.prescriptionCount as juneCount,
    prescriptionSecondQuarterInfo.prescriptionCount as secondQuarterCount,
    prescriptionJulyInfo.prescriptionCount as julyCount,
    prescriptionAugInfo.prescriptionCount as augCount,
    prescriptionSeptInfo.prescriptionCount as sepCount,
    prescriptionThirdQuarterInfo.prescriptionCount as thirdQuarterCount,
    prescriptionOctInfo.prescriptionCount as octCount,
    prescriptionNovInfo.prescriptionCount as novCount,
    prescriptionDecInfo.prescriptionCount as decCount,
    prescriptionFourthQuarterInfo.prescriptionCount as fourthQuarterCount,
    prescriptionYearInfo.prescriptionCount as yearCount
FROM rpm_prescription prescription 
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where extract(month from create_date)=1 AND prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionJanInfo on (prescriptionJanInfo.prescriptionYear = extract(year from prescription.create_date))
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where extract(month from create_date)=2 AND prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionFebInfo on (prescriptionFebInfo.prescriptionYear = extract(year from prescription.create_date))
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where extract(month from create_date)=3 AND prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionMarInfo on (prescriptionMarInfo.prescriptionYear = extract(year from prescription.create_date))
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where extract(month from create_date)in (1,2,3) AND prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionFirstQuarterInfo on (prescriptionFirstQuarterInfo.prescriptionYear = extract(year from prescription.create_date))
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where extract(month from create_date)=4 AND prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionAprInfo on (prescriptionAprInfo.prescriptionYear = extract(year from prescription.create_date))
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where extract(month from create_date)=5 AND prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionMayInfo on (prescriptionMayInfo.prescriptionYear = extract(year from prescription.create_date))
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where extract(month from create_date)=6 AND prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionJuneInfo on (prescriptionJuneInfo.prescriptionYear = extract(year from prescription.create_date))
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where extract(month from create_date)in (4,5,6) AND prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionSecondQuarterInfo on (prescriptionSecondQuarterInfo.prescriptionYear = extract(year from prescription.create_date))
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where extract(month from create_date)=7 AND prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionJulyInfo on (prescriptionJulyInfo.prescriptionYear = extract(year from prescription.create_date))
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where extract(month from create_date)=8 AND prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionAugInfo on (prescriptionAugInfo.prescriptionYear = extract(year from prescription.create_date))
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where extract(month from create_date)=9 AND prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionSeptInfo on (prescriptionSeptInfo.prescriptionYear = extract(year from prescription.create_date))
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where extract(month from create_date)in (7,8,9) AND prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionThirdQuarterInfo on (prescriptionThirdQuarterInfo.prescriptionYear = extract(year from prescription.create_date))
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where extract(month from create_date)=10 AND prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionOctInfo on (prescriptionOctInfo.prescriptionYear = extract(year from prescription.create_date))
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where extract(month from create_date)=11 AND prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionNovInfo on (prescriptionNovInfo.prescriptionYear = extract(year from prescription.create_date))
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where extract(month from create_date)=12 AND prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionDecInfo on (prescriptionDecInfo.prescriptionYear = extract(year from prescription.create_date))
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where extract(month from create_date)in (10,11,12) AND prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionFourthQuarterInfo on (prescriptionFourthQuarterInfo.prescriptionYear = extract(year from prescription.create_date))
LEFT JOIN (
        select extract(year from create_date) as prescriptionYear
            ,count(*) as prescriptionCount 
        FROM rpm_prescription 
        where prescription_status = '4'
        GROUP BY extract(year from create_date)
    )  as prescriptionYearInfo on (prescriptionYearInfo.prescriptionYear = extract(year from prescription.create_date))

where extract(year from prescription.create_date)::text=/*condition.prescriptionYear*/
limit 1

