package com.szm.neo4j.springneo4j.control;

import com.szm.neo4j.springneo4j.entity.Dept;
import com.szm.neo4j.springneo4j.entity.RelationShip;
import com.szm.neo4j.springneo4j.repository.DeptRepository;
import com.szm.neo4j.springneo4j.repository.RelationShipRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class SystemController {
    @Resource
    private DeptRepository deptRepository;
    @Resource
    private RelationShipRepository relationShipRepository;

    public static final String SUCCESS_RESULT = "success";

    @GetMapping("create")
    public void create(){
        Dept CEO = Dept.builder().deptName("CEO").build();
        Dept dept1 = Dept.builder().deptName("设计部").build();
        Dept dept11 = Dept.builder().deptName("设计1组").build();
        Dept dept12 = Dept.builder().deptName("设计2组").build();

        Dept dept2 = Dept.builder().deptName("技术部").build();
        Dept dept21 = Dept.builder().deptName("前端技术部").build();
        Dept dept22 = Dept.builder().deptName("后端技术部").build();
        Dept dept23 = Dept.builder().deptName("测试技术部").build();
        List<Dept> depts = new ArrayList<>(Arrays.asList(CEO,dept1,dept11,dept12,dept2,dept21,dept22,dept23));
        deptRepository.saveAll(depts);

        RelationShip relationShip1 = RelationShip.builder().parent(CEO).child(dept1).build();
        RelationShip relationShip2 = RelationShip.builder().parent(CEO).child(dept2).build();
        RelationShip relationShip3 = RelationShip.builder().parent(dept1).child(dept11).build();
        RelationShip relationShip4 = RelationShip.builder().parent(dept1).child(dept12).build();
        RelationShip relationShip5 = RelationShip.builder().parent(dept2).child(dept21).build();
        RelationShip relationShip6 = RelationShip.builder().parent(dept2).child(dept22).build();
        RelationShip relationShip7 = RelationShip.builder().parent(dept2).child(dept23).build();
        List<RelationShip> relationShips = new ArrayList<>(Arrays.asList(relationShip1,relationShip2,relationShip3,relationShip4,relationShip5
                ,relationShip6,relationShip7));
        relationShipRepository.saveAll(relationShips);
    }

    @GetMapping("get")
    public RelationShip get(Long id){
        Optional<RelationShip> byId = relationShipRepository.findById(id);
        return byId.orElse(null);
    }

    @GetMapping("deleteRelationShip")
    public void deleteRelationShip(Long id){
        relationShipRepository.deleteById(id);
    }

    @GetMapping("deleteDept")
    public void deleteDept(Long id){
        deptRepository.deleteById(id);
    }

    @GetMapping("deleteAll")
    public void deleteAll(){
        deptRepository.deleteAll();
        relationShipRepository.deleteAll();
    }
}

