package com.xtl.controller;

import com.xtl.pojo.Doctor;
import com.xtl.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 31925
 */
@Controller
@RequestMapping("/restful")
public class RestfulController {

    @Resource
    private DoctorService doctorService;

    @RequestMapping("doctor")
    public String findAllDoctors(Model model){
        List<Doctor> doctorList = doctorService.findAll();
        model.addAttribute("doctorList",doctorList);
        return "doctor/doctor_list";
    }

    @GetMapping("doctor/{id}")
    public String getDoctorById(@PathVariable("id") Integer id, Model model){
        Doctor doctor= doctorService.getDoctorById(id);
        model.addAttribute("doctor",doctor);
        return "doctor/doctor_info";
    }

    @PostMapping("doctor/{id}")
    public String addDoctor(@PathVariable("id") Integer id,Doctor doctor){
        doctor.setId(id);
        doctorService.addDoctor(doctor);
        return "redirect:.";
    }

    @DeleteMapping("doctor/{id}")
    public String deleteDoctor(@PathVariable("id") Integer id){
        doctorService.deleteDoctorById(id);
        return "redirect:.";
    }

    @PutMapping("doctor/{id}")
    public String updateDoctor(@PathVariable("id") Integer id,Doctor doctor){
        doctor.setId(id);
        doctorService.updateDoctor(doctor);
        return "redirect:.";
    }

}
