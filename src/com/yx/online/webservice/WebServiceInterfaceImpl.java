package com.yx.online.webservice;

import com.lidroid.xutils.http.RequestParams;
import com.yx.online.tools.HealthUtil;


public class WebServiceInterfaceImpl implements IWebServiceInterface{

	@Override
	public RequestParams queryDoctorList(String expertType, String onLineType, String teamId)
	{
		return HealthUtil.getRequestParams("BUS2001", new String[]{"expertType","onLineType","teamId"},new Object[]{expertType,onLineType,teamId});
	}

	@Override
	public RequestParams queryTeamList(String hospitalId,String expertType)
	{
		return HealthUtil.getRequestParams("BUS2002", new String[]{"hospitalId","expertType"},new Object[]{hospitalId,expertType});
	}

	@Override
	public RequestParams queryOrderDoctorList(String hospitalId,String teamId,String doctorId)
	{
		return HealthUtil.getRequestParams("BUS2003", new String[]{"hospitalId","teamId","doctorId"},new Object[]{hospitalId,teamId,doctorId});
	}

	@Override
	public RequestParams queryOrderByDoctorIdList(String hospitalId,String userId,String orderTeamId,String doctorId,String weekStr,String dateStr)
	{
		return HealthUtil.getRequestParams("BUS2004", new String[]{"hospitalId","userId","orderTeamId","doctorId","week","date"},new Object[]{hospitalId,userId,orderTeamId,doctorId,weekStr,dateStr});
	}

	@Override
	public RequestParams queryUser(String telephone, String password)
	{
		return HealthUtil.getRequestParams("BUS2005", new String[]{"telephone","password"},new Object[]{telephone,password});
	}

	@Override
	public RequestParams addUserRegisterOrder(String hospitalId,String userId, String registerId, String doctorId, String doctorName, String orderNum,
			String orderFee, String registerTime, String userName, String userNo, String userTelephone,String sex, String teamId,
			String teamName)
	{
		return HealthUtil.getRequestParams("BUS2006", new String[]{"hospitalId", "userId", "registerId", "doctorId", "doctorName", "orderNum",  "orderFee", "registerTime", "userName", "userNo", "userTelephone","sex", "teamId", "teamName" },new Object[]{hospitalId , userId       , registerId   , doctorId     , doctorName   , orderNum     ,  orderFee     , registerTime , userName     , userNo       , userTelephone, sex,teamId       , teamName});
	}

	@Override
	public RequestParams addUserQuestion(String userQuestion)
	{
		return HealthUtil.getRequestParams("BUS2007", new String[]{"userQestion"},new Object[]{userQuestion});
	}

	@Override
	public RequestParams getUserQuestionsByDoctorId(String doctorId)
	{
		return HealthUtil.getRequestParams("BUS2008", new String[]{"doctorId"},new Object[]{doctorId});
	}

	@Override
	public RequestParams getHospitals(String hospitalId)
	{
		return HealthUtil.getRequestParams("BUS20010", new String[]{"hospitalId"},new Object[]{hospitalId});
	}

	@Override
	public RequestParams addUser(String user)
	{
		return HealthUtil.getRequestParams("BUS20011", new String[]{"user"},new Object[]{user});
	}

	@Override
	public RequestParams updateUser(String user)
	{
		return HealthUtil.getRequestParams("BUS20012", new String[]{"user"},new Object[]{user});
	}

	@Override
	public RequestParams getOrderNormalNum(String teamId, String registerTime)
	{
		return HealthUtil.getRequestParams("BUS20013", new String[]{"teamId","registerTime"},new Object[]{teamId,registerTime});
	}

	@Override
	public RequestParams getUserOrderById(String userId,String hospitalId)
	{
		// TODO Auto-generated method stub
		return HealthUtil.getRequestParams("BUS20014", new String[]{"userId","hospitalId"},new Object[]{userId,hospitalId});
	}

	@Override
	public RequestParams getUserQuestionsByUserId(String userId,String hospitalId)
	{
		return HealthUtil.getRequestParams("BUS20015", new String[]{"userId","hospitalId"},new Object[]{userId,hospitalId});
	}

	@Override
	public RequestParams getUserQuestionsByIds(String questionId)
	{
		return HealthUtil.getRequestParams("BUS20016", new String[]{"questionId"},new Object[]{questionId});
	}

	@Override
	public RequestParams getTeamByHospitalId(String hospitalId)
	{
		return HealthUtil.getRequestParams("BUS20017", new String[]{"hospitalId"},new Object[]{hospitalId});
	}
	
	@Override
	public RequestParams getNewsByHospitalId(String hospitalId,String type,String typeId)
	{
		return HealthUtil.getRequestParams("BUS20018", new String[]{"hospitalId","type","typeId"},new Object[]{hospitalId,type,typeId});
	}

	@Override
	public RequestParams checkNewVersion(String param) {
		return HealthUtil.getRequestParams("BUS20019", new String[] {
				"param"}, new Object[] {param});
	}

	@Override
	public RequestParams getNewsType(String hospitalId, String type)
	{
		return HealthUtil.getRequestParams("BUS20020", new String[]{"hospitalId","type"},new Object[]{hospitalId,type});
	}
	
	@Override
	public RequestParams getAuthCode(String accNbr,String type)
	{
		return HealthUtil.getRequestParams("BUS20021", new String[]{"accNbr","type"},new Object[]{accNbr,type});
	}

	public RequestParams checkAuthCode(String accNbr,String authCode)
	{
		return HealthUtil.getRequestParams("BUS20022", new String[]{"accNbr","authCode"},new Object[]{accNbr,authCode});
	}

	@Override
	public RequestParams orderPay(String orderdId,String payState) 
	{
		return HealthUtil.getRequestParams("BUS20023", new String[]{"orderdId","payState"},new Object[]{orderdId,payState});
	}

	@Override
	public RequestParams getRsaSign(String orderdId)
	{
		return HealthUtil.getRequestParams("BUS20024", new String[]{"orderdId"},new Object[]{orderdId});
	}
	
	@Override
	public RequestParams getDoctorQueById(String doctorId,String teamId)
	{
		return HealthUtil.getRequestParams("BUS20025", new String[]{"doctorId","teamId"},new Object[]{doctorId,teamId});
	}
	
	@Override
	public RequestParams getDoctorQuestionsById(String doctorId)
	{
		return HealthUtil.getRequestParams("BUS20026", new String[]{"doctorId"},new Object[]{doctorId});
	}

	@Override
	public RequestParams doctorLogin(String userName, String password) {
		return HealthUtil.getRequestParams("BUS20027", new String[]{"userName","password"},new Object[]{userName,password});
	}
	
	@Override
	public RequestParams getDoctorQuestionsByType(String doctorId, String type) {
		return HealthUtil.getRequestParams("BUS20028", new String[]{"doctorId","type"},new Object[]{doctorId,type});
	}

	@Override
	public RequestParams updateQuestionDisplay(String id, String display) {
		return HealthUtil.getRequestParams("BUS20029", new String[]{"id","display"},new Object[]{id,display});
	}

	@Override
	public RequestParams updateDoctorCopy(String id, String content) {
		return HealthUtil.getRequestParams("BUS20030", new String[]{"id","content"},new Object[]{id,content});
	}

	@Override
	public RequestParams deleteDoctorCopy(String id) {
		return HealthUtil.getRequestParams("BUS20031", new String[]{"id"},new Object[]{id });
	}
}