package myBatis2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import myBatis2.model.Dept;
import myBatis2.service.DeptService;

@Controller
public class DeptController {
	@Autowired
	private DeptService ds;

	// 부서 목록
	@RequestMapping("deptList.do")
	public String deptList(Model model) {
		List<Dept> list = ds.list();
		model.addAttribute("list", list);
		return "deptList";
	}

	// 부서 등록폼
	@RequestMapping("deptInsertForm.do")
	public String deptInsertForm() {
		return "deptInsertForm";
	}

	// 부서 등록
	@RequestMapping("deptInsert.do")
	public String deptInsert(@ModelAttribute Dept dept, 
			                 Model model) {
		Dept dt = ds.select(dept.getDeptno());	// 부서번호 중복 검사
		if (dt == null) {	// 중복 되지 않은 부서번호
			int result = ds.insert(dept);
			model.addAttribute("result", result);
		} else {	// 중복된 부서번호
			model.addAttribute("msg", "이미 있는 데이터입니다");
			model.addAttribute("result", -1);
		}
		return "deptInsert";
	}

	@RequestMapping("deptUpdateForm.do")
	public String deptUpdateForm(int deptno, Model model) {
		Dept dept = ds.select(deptno);
		model.addAttribute("dept", dept);
		return "deptUpdateForm";
	}

	@RequestMapping("deptUpdate.do")
	public String deptUpdate(Dept dept, Model model) {
		int result = ds.update(dept);
		model.addAttribute("result", result);
		return "deptUpdate";
	}

	@RequestMapping("deptDelete.do")
	public String deptDelete(int deptno, Model model) {
		int result = ds.delete(deptno);
		model.addAttribute("result", result);
		return "deptDelete";
	}
}