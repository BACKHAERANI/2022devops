package com.Assembble.carbble.service;

import com.Assembble.carbble.dao.*;
import com.Assembble.carbble.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public class DatabaseServiceImpl implements DatabaseService{


    @Autowired
    CarDAOImpl car;
    @Autowired
    MemberDAOImpl member;

    @Autowired
    ReservationDAOImpl reservation;

    @Autowired
    ReturnDAOImpl returnDAO;

    @Autowired
    UserDAOImpl user;

    @Autowired
    CheckDAOImpl checkDAO;

    @Autowired
    EmergencyDAOImpl emergencyDAO;


    ///////Member///////////////////////////////////////////////////////
    @Override
    public UserDTO loginUser(MemberDTO dto){
        return member.select(dto);
    }




    ///////reservation///////////////////////////////////////////////////////
    @Override
    public List<ReservationDTO> putReservation(Date startdate, Date enddate)
    {
        return reservation.select(startdate, enddate);
    }


    @Override
    public  Integer selectCount(ReservationDTO dto)
    {
        return reservation.selectCount(dto);
    }


    @Override
    public int addReservation(BReservationDTO dto)
    {
        return reservation.insert(dto);
    }



    @Override
    public int removeReservation(int reservation_id){return reservation.delete(reservation_id);}


    @Override
    public int putReservationReturnId(ReservationPutReturnIdDTO rdto){
        return reservation.update( rdto);
    }


    @Override
    public  ReservationDTO selectReservationId(int reservation_id){return  reservation.selectReservationId(reservation_id);}

    ///////car///////////////////////////////////////////////////////

    @Override
    public List<CarDTO> getCar()
    { return car.select();}


    @Override
    public int putUseStatus(int car_id, int use_status)
    {return car.updateUseStatus(car_id, use_status);}


    @Override
    public CarDTO getCar(int car_id) {
        return car.select(car_id);
    }

    @Override
    public int putCarmileage(int car_id, CarPutDAO cdto){
        return car.updatemileage(car_id, cdto);
    }





    ///////return///////////////////////////////////////////////////////

    @Override
    public List<ReturnDTO> getReturn()
    {
        return returnDAO.select();
    }

    @Override
    public ReturnDTO selectReturn(int reservation_id){ return returnDAO.selectReturnId(reservation_id);}


    @Override
    public int postReturn(ReturnDTO dto){
        return returnDAO.insertReturn(dto);
    }

    @Override
    public List<ReturnDTO> selectReturnByReturnIdList(List<Integer> list) {
        return returnDAO.selectReturnByReturnIdList(list);
    }

    @Override
    public List<SumRefuelingDTO> sumRefueling(PutDateDTO dto) {
        return returnDAO.sumRefueling(dto);
    }

    @Override
    public List<CountRefuelingDTO> countRefueling(PutDateDTO dto) {
        return returnDAO.countRefueling(dto);
    }


    ///////user///////////////////////////////////////////////////////

    @Override
    public List<UserDTO> getUser(){

        return user.userSelect();
    }

    @Override
    public int addUser(UserDTO dto){

        return user.userInsert(dto);
    }

    @Override
    public int removeUser(int user_id){

        return user.userDelete(user_id);
    }

    @Override
    public int modifyPwUser(int user_id, String password){
        return user.userPwUpdate(user_id, password);
    }

    @Override
    public int modifyUser(int user_id, UserPutDTO dto){
        return user.userUpdate(user_id, dto);
    }


    @Override
    public  int selectCountUser(UserDTO dto){return user.selectCountUser(dto);}




    ///////check///////////////////////////////////////////////////////


    @Override
    public List<CheckDTO> selectCheck(java.sql.Date startdate, java.sql.Date enddate) {
        return checkDAO.selectCheck(startdate, enddate);
    }

    @Override
    public int insertCheck(CheckDTO dto) {
        return checkDAO.insertCheck(dto);
    }

    @Override
    public int updateCheck(int check_id, CheckPutDTO dto) {
        return checkDAO.updateCheck(check_id, dto);
    }

    @Override
    public int deleteCheck(int check_id) {
        return checkDAO.deleteCheck(check_id);
    }



    ///////emergency///////////////////////////////////////////////////////

    @Override
    public int insertEmergency(EmergencyDTO dto) {
        return emergencyDAO.insertEmergency(dto);
    }

    @Override
    public List<EmergencyDTO> selectEmergency(java.sql.Date startdate, java.sql.Date endate) {
        return emergencyDAO.selectEmergency(startdate, endate);
    }

    @Override
    public int updateEmergency(int emergency_id, EmergencyPutDTO dto) {
        return emergencyDAO.updateEmergency(emergency_id, dto);
    }


}
