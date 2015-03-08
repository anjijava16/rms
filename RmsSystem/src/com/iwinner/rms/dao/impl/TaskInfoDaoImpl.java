package com.iwinner.rms.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.dao.TaskInfoDaoIF;
import com.iwinner.rms.expections.DaoException;
import com.iwinner.rms.model.TaskInfo;
import com.iwinner.rms.model.TaskPrioritys;
import com.iwinner.rms.model.TaskType;
import com.iwinner.rms.utils.HibernateUtils;

public class TaskInfoDaoImpl implements TaskInfoDaoIF {

	private static  Logger LOGGER=Logger.getLogger(TaskInfoDaoImpl.class);
	public Integer addTasks(TaskInfo taskInfo) throws DaoException {
		Integer addTaskID = RMSConstants.NEW_TASK_ID_PROCESSING;
		Integer addTaskIDInfo=getTaskId();
		if(addTaskIDInfo==null){
			taskInfo.setTaskId(RMSConstants.ADD_ONE);
		}else{
			taskInfo.setTaskId(addTaskIDInfo + RMSConstants.ADD_ONE);
		}
		Session session = HibernateUtils.getSession();
		TaskInfo taskInfy = (TaskInfo) session.get(TaskInfo.class,	taskInfo.getTaskId());
		session.beginTransaction().begin();
		if (taskInfy == null) {
			session.save(taskInfo);
			session.beginTransaction().commit();
			addTaskID = RMSConstants.NEW_TASK_ID_CREATED;
		} else {
			session.beginTransaction().rollback();
			addTaskID = RMSConstants.NEW_TASK_ID_EXISTS;
		}
		return addTaskID;
	}

	public TaskInfo viewTaskInfo(Date isVerifyDate) throws DaoException {
		Session session = HibernateUtils.getSession();
       TaskInfo taskInfo=(TaskInfo)session.load(TaskInfo.class, isVerifyDate);        
		return taskInfo;
	}

	public List<TaskInfo> viewAllTasksInfo() throws DaoException {
		
		return null;
	}

	public List<TaskInfo> viewInfoFromToDates(Date fromDate, Date toDate)throws DaoException {
		Session session=HibernateUtils.getSession();
		Query query=session.createQuery(RMSConstants.SELECT_TASKINFO);
		List<TaskInfo> taskInfo=(List<TaskInfo>)query.list();
		return taskInfo;
	}

	public Integer deleteTask(Integer taskId) throws DaoException {
		Session session=HibernateUtils.getSession();
		TaskInfo taskInfo =(TaskInfo)session.get(TaskInfo.class, taskId);
		if(taskInfo!=null){
		session.delete(taskInfo);
		}else{
			//
		}
		return null;
	}

	public Integer updateTaskInfo(TaskInfo taskInfo) throws DaoException {

		Session session=HibernateUtils.getSession();
		TaskInfo taskIn=(TaskInfo)session.get(TaskInfo.class, taskInfo.getTaskId());
		session.beginTransaction().begin();
		if(taskIn!=null){
			session.update(taskInfo);
			session.beginTransaction().commit();	
		}else{
			session.beginTransaction().rollback();
		}
		return null;
	}

	public List<TaskInfo> getFeatureTasksInfoList() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TaskInfo> priporityTasks(String priportyTasks) throws DaoException {
		Session session=HibernateUtils.getSession();
		Query query=session.createQuery(RMSConstants.SELECT_HIGH_PRIPORTY);
		query.setString("TASK_PRIPORTY", priportyTasks);
		List<TaskInfo> listOfHighPri=query.list();
		return listOfHighPri;
	}

	public List<TaskInfo> urgetnPriporityTasks() throws DaoException {
		Session session=HibernateUtils.getSession();
		Query query=session.createQuery(RMSConstants.SELECT_HIGH_PRIPORTY);
		String priporty=TaskPrioritys.PRIORITY_1.getPriority();
		query.setString("TASK_PRIPORTY", TaskPrioritys.PRIORITY_1.getPriority());
		List<TaskInfo> listOfHighPri=query.list();
		return listOfHighPri;

	}
	public static Integer getTaskId() throws DaoException {
		Session session = HibernateUtils.getSession();
		Query query = session.createQuery(RMSConstants.SELECT_MAX_TASK_ID);
		List<Integer> taskId = query.list();
		System.out.println("Inside the getTaskID()");
		Integer defaultTaskId = 0;
		for (Integer updateTaskID : taskId) {
			System.out.println(updateTaskID);
			defaultTaskId = updateTaskID;
		}
		return defaultTaskId;
	}

	public  Integer countOfTaskId()throws DaoException{
		Integer taskId=RMSConstants.TASK_ID_PROCESSING;
		LOGGER.info("***** start of countOfTaskID() is processing ***** ") ;
		   try{
     		Session session=HibernateUtils.getSession();
		    Query query=session.createQuery(RMSConstants.SELECT_COUNT_TASK_ID);
	        List<Long> countOfTaskId=query.list();
	        Long id=(Long)countOfTaskId.get(0);
	        taskId=id.intValue();
	        LOGGER.info("***** processing Result of taskId=["+taskId+"]"+"*****") ;
		   }catch(Exception e){
			   taskId=RMSConstants.TASK_ID_FAILED;
			   LOGGER.info("##### Error Inside  countOfTaskID() is processing ##### "+e.getMessage()) ;
		   }
		   LOGGER.info("***** end of countOfTaskID() is processing ***** ") ;
		return taskId;
	}
	
	public static void main(String[] args) throws Exception {

		TaskInfoDaoImpl tD = new TaskInfoDaoImpl();
		TaskPrioritys ts[]=TaskPrioritys.values();
		Map<String,List> map=new HashMap<String,List>();
       List<String> listOF=new ArrayList<String>();
        for(TaskPrioritys tq:ts){
        	listOF.add(tq.getPriority());
        }
        map.put("tprioritys", listOF);
        TaskType tType[]=TaskType.values();
        List<String> listOfTaskType=new ArrayList<String>();
        for(TaskType type:tType){
        	listOfTaskType.add(type.getMessage());
        }
        map.put("ttypes", listOfTaskType);
        System.out.println("Iterator Starting");
        Iterator<Map.Entry<String, List>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
          Map.Entry<String, List> entry = entries.next();
          String key = entry.getKey();
          List value = entry.getValue();
          System.out.println(key+"  "+value);
        }
        System.out.println("Iterator Ending");
		System.out.println(listOF);
/*		 Integer taskID=tD.getTaskId();
		 System.out.println(taskID);
		TaskInfo taskInfo = new TaskInfo();
		taskInfo.setComments("Meet X Person at Hyderabad");
		taskInfo.setTaskDate(new Date());
		taskInfo.setTaskImpComments("Discuss on Hadoop 2.x with Project explaination ");
		taskInfo.setTaskPriporty(TaskPrioritys.PRIORITY_2.getPriority());
		taskInfo.setTaskName("Meeting with Friend");
		taskInfo.setTaskTime(new Timestamp(new Date().getTime()));
		taskInfo.setTaskUpdatedBy("anji");
		taskInfo.setTaskUpdateTime(new Timestamp(new Date().getTime()));
		Integer taskGetId = tD.addTasks(taskInfo);
		System.out.println(taskGetId);
*/	}
}
