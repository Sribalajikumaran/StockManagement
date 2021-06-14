/**
 * 
 */
package com.praveen.stockmanagement.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;

import com.opencsv.CSVReader;
import com.praveen.stockmanagement.domains.Stock;
import com.praveen.stockmanagement.domains.StockDto;
import com.praveen.stockmanagement.services.StockService;

/**
 * @author Madan
 *
 */
@Controller
@RequestMapping("/stocks")
public class StockController {


	@Autowired
	private StockService service;
	
	@GetMapping({ "/", "" })
	public String index(Model model) {
		List<Stock> stocks=service.findAll();
		model.addAttribute("stocks", stocks);
		return "stocks/index";
		
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("stock", new Stock());
		return "stocks/add";
	}
	
	
	@PostMapping("/save")
	public String save(@ModelAttribute("stock") Stock stock) {
		stock=service.save(stock);
		return "redirect:/stocks";
		
	}
	
	@GetMapping("/{id}")
	public String getStocks(Model model, @PathVariable("id") Long id) {
		Stock stock=service.findById(id);
		model.addAttribute("stock", service.findById(id));
		return "stocks/detail";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id")Long id,Model model) {
		Stock stock=service.findById(id);
		model.addAttribute("stock", stock);
		return "stocks/edit";	
	}
	
	@PostMapping("/{id}/update")
	public String update(@ModelAttribute Stock stock,@PathVariable("id") Long id) {
		stock.setId(id);
		service.save(stock);
		return "redirect:/stocks/" + id;
		
	}
	
	@GetMapping("/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		Stock stock=service.findById(id);
		service.delete(stock);
		return "redirect:/stocks";
		
	}
	
	@GetMapping("/downloadCsv")
	public String downloadCsv(@ModelAttribute("stockDto")StockDto stockDto) {
		//model.addAttribute("stockDto", new StockDto());
		return "stocks/stockUpload";
	}
	
	@RequestMapping(value = "/download/csv")
    public String  downloadCSV(HttpServletResponse response,HttpServletRequest request) throws IOException {
		
		String path=request.getSession().getServletContext().getRealPath("/")+"resources"+"/";
		System.out.println("path======"+path);
		//FileInputStream fis=new FileInputStream(new File(path+"templat.csv"));
		InputStream input = new FileInputStream(new File(path+"templat.csv"));
		Reader reader = new InputStreamReader(input, "UTF-8");
		//reader.
		//reader.
		return null;	
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file,ModelMap modelMap,HttpServletResponse response,HttpServletRequest request) throws IOException {
		modelMap.addAttribute("file",file);
		MultipartFile mf=file;
		Random rand=new Random();
		int rand_int1 = rand.nextInt(1000);
		if(mf!=null && !mf.getOriginalFilename().isEmpty()) {
			//Long milli=new Long();
			String path="J:\\files\\";
			FileOutputStream fout=new FileOutputStream(path+rand_int1+mf.getOriginalFilename());
			BufferedOutputStream bout=new BufferedOutputStream(fout);
			BufferedInputStream bin=  new BufferedInputStream(mf.getInputStream());
			int read=0;
			byte buf[] =new byte[2048];
			while((read =bin.read(buf))!=-1) {
				bout.write(buf, 0, read);
			}
			bout.flush();
			bout.close();
			bin.close();
			fout.flush();
			fout.close();
			FileReader filereader = new FileReader(path+rand_int1+mf.getOriginalFilename());
			CSVReader csvReader = new CSVReader(filereader);
	        String[] nextRecord;
	        int i=0;
	        List<Stock> stList=new ArrayList<Stock>();
	        while ((nextRecord = csvReader.readNext()) != null) {
	        	Stock st=new Stock();
	        	int j=0;
	        	int k=0;
	            for (String cell : nextRecord) {
	                if(i>4) {
	                	if(j==0) {
	                		st.setName(cell);
	                	}else if(j==1) {
	                		st.setQuantity(cell);
	                	}else if(j==2) {
	                		st.setPrice(cell);
	                	}else if(j==3) {
	                		st.setProductnumber(cell);
	                	}else if(j==4) {
	                		st.setDescription(cell);
	                	} 
	                	j++;
	                }
	                i++;
	            }
	            k++;
	            System.out.print("count============"+k);
	            if(st.getName()!=null) {
	            	stList.add(st);
	            }
	        }
	        if(stList.size()>0) {
	        	service.saveStockList(stList);
	        }
		}
		return "redirect:/stocks";
		
	}
}
