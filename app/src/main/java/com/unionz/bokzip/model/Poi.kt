package com.unionz.bokzip.model

// API에서 받아올 현재 위치 데이터 구조
class Poi() {
    //POI 의  id
    private lateinit var id : String
    //POI 의 name
    private lateinit var name : String
    //POI 에 대한 전화번호
    private lateinit var telNo : String
    //시설물 입구 위도 좌표
    private lateinit var frontLat: String
    //시설물 입구 경도 좌표
    private lateinit var frontLon: String
    //중심점 위도 좌표
    private lateinit var noorLat: String
    //중심점 경도 좌표
    private lateinit var noorLon: String
    //표출 주소 대분류명
    private lateinit var upperAddrName: String
    //표출 주소 중분류명
    private lateinit var middleAddrName: String
    //표출 주소 소분류명
    private lateinit var lowerAddrName: String
    //표출 주소 세분류명
    private lateinit var detailAddrName: String
    //본번
    private lateinit var firstNo: String
    //부번
    private lateinit var secondNo: String
    //도로명
    private lateinit var roadName: String
    //건물번호 1
    private lateinit var firstBuildNo: String
    //건물번호 2
    private lateinit var secondBuildNo: String
    //업종 대분류명
    private lateinit var mlClass: String
    //거리(km)
    private lateinit var radius: String
    //업소명
    private lateinit var bizName: String
    //시설목적
    private lateinit var upperBizName: String
    //시설분류
    private lateinit var middleBizName: String
    //시설이름 ex) 지하철역 병원 등
    private lateinit var lowerBizName: String
    //상세 이름
    private lateinit var detailBizName: String
    //길안내 요청 유무
    private lateinit var rpFlag: String
    //주차 가능유무
    private lateinit var parkFlag: String
    //POI 상세정보 유무
    private lateinit var detailInfoFlag: String
    //소개 정보
    private lateinit var desc: String

    fun getId() : String {
        return id
    }

    fun setId(id : String){
        this.id = id
    }

    fun getName() : String {
        return name
    }

    fun setName(name : String){
        this.name = name
    }

    fun getTelNo() : String {
        return telNo
    }

    fun setTelNo(telNo : String){
        this.telNo = telNo
    }

    fun getFrontLat() : String {
        return frontLat
    }

    fun setFrontLat(frontLat : String){
        this.frontLat = frontLat
    }

    fun getFrontLon() : String {
        return frontLon
    }

    fun setFrontLon(frontLon : String){
        this.frontLon = frontLon
    }

    fun getNoorLat() : String {
        return noorLat
    }

    fun setNoorLat(noorLat : String){
        this.noorLat = noorLat
    }

    fun getNoorLon() : String {
        return noorLon
    }

    fun setNoorLon(noorLon : String){
        this.noorLon = noorLon
    }

    fun getUpperAddrName() : String {
        return upperAddrName
    }

    fun setUpperAddrName(upperAddrName: String){
        this.upperAddrName = upperAddrName
    }

    fun getMiddleAddrName() : String{
        return middleAddrName
    }

    fun setMiddleAddrName(middleAddrName : String){
        this.middleAddrName = middleAddrName
    }

    fun getLowerAddrName() : String {
        return lowerAddrName
    }

    fun setLowerAddrName(lowerAddrName: String){
        this.lowerAddrName = lowerAddrName
    }

    fun getDetailAddrName(): String? {
        return detailAddrName
    }

    fun setDetailAddrName(detailAddrName: String?) {
        this.detailAddrName = detailAddrName!!
    }

    fun getMlClass(): String? {
        return mlClass
    }

    fun setMlClass(mlClass: String?) {
        this.mlClass = mlClass!!
    }

    fun getFirstNo(): String? {
        return firstNo
    }

    fun setFirstNo(firstNo: String?) {
        this.firstNo = firstNo!!
    }

    fun getSecondNo(): String? {
        return secondNo
    }

    fun setSecondNo(secondNo: String?) {
        this.secondNo = secondNo!!
    }

    fun getRoadName(): String? {
        return roadName
    }

    fun setRoadName(roadName: String?) {
        this.roadName = roadName!!
    }

    fun getFirstBuildNo(): String? {
        return firstBuildNo
    }

    fun setFirstBuildNo(firstBuildNo: String?) {
        this.firstBuildNo = firstBuildNo!!
    }

    fun getSecondBuildNo(): String? {
        return secondBuildNo
    }

    fun setSecondBuildNo(secondBuildNo: String?) {
        this.secondBuildNo = secondBuildNo!!
    }

    fun getRadius(): String? {
        return radius
    }

    fun setRadius(radius: String?) {
        this.radius = radius!!
    }

    fun getBizName(): String? {
        return bizName
    }

    fun setBizName(bizName: String?) {
        this.bizName = bizName!!
    }

    fun getUpperBizName(): String? {
        return upperBizName
    }

    fun setUpperBizName(upperBizName: String?) {
        this.upperBizName = upperBizName!!
    }

    fun getMiddleBizName(): String? {
        return middleBizName
    }

    fun setMiddleBizName(middleBizName: String?) {
        this.middleBizName = middleBizName!!
    }

    fun getLowerBizName(): String? {
        return lowerBizName
    }

    fun setLowerBizName(lowerBizName: String?) {
        this.lowerBizName = lowerBizName!!
    }

    fun getDetailBizName(): String? {
        return detailBizName
    }

    fun setDetailBizName(detailBizName: String?) {
        this.detailBizName = detailBizName!!
    }

    fun getRpFlag(): String? {
        return rpFlag
    }

    fun setRpFlag(rpFlag: String?) {
        this.rpFlag = rpFlag!!
    }

    fun getParkFlag(): String? {
        return parkFlag
    }

    fun setParkFlag(parkFlag: String?) {
        this.parkFlag = parkFlag!!
    }

    fun getDetailInfoFlag(): String? {
        return detailInfoFlag
    }

    fun setDetailInfoFlag(detailInfoFlag: String?) {
        this.detailInfoFlag = detailInfoFlag!!
    }

    fun getDesc(): String? {
        return desc
    }

    fun setDesc(desc: String?) {
        this.desc = desc!!
    }
}
