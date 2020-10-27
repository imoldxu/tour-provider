package com.x.hotels.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.x.hotels.context.ErrorCode;
import com.x.hotels.context.HandleException;
import com.x.hotels.context.bo.PriceBo;
import com.x.hotels.service.ExcelPriceService;
import com.x.hotels.service.PriceService;
import com.x.tools.util.DateUtils;
import com.x.tools.util.ExcelExportUtil;
import com.x.tools.util.WDWUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/excelprice")
@Api("Excel价格文件")
public class ExcelPriceController {

	@Autowired
	private PriceService priceService;
	@Autowired
	private ExcelPriceService excelUtils;

	@CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "新增上传供应商价格文件", notes = "新增上传药品信息")
	public void uploadByExcel(@RequestParam(value = "providerId") String providerId,
			@RequestPart(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Methods", "POST");

		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
		long size = file.getSize();
		if (StringUtils.isEmpty(fileName) && size == 0) {
			throw new HandleException(ErrorCode.ARG_ERROR, "文件不存在或没有内容");
		}
		
		if (!WDWUtil.isExcel2003(fileName) && !WDWUtil.isExcel2007(fileName)) {
			// errorMsg = "文件名不是excel格式";
			throw new HandleException(ErrorCode.ARG_ERROR, "只支持excel文件");
		}
		boolean isExcel2003 = true;
		// 根据文件名判断文件是2003版本还是2007版本
		if (WDWUtil.isExcel2007(fileName)) {
			isExcel2003 = false;
		}
		
		List<PriceBo> priceList;
		InputStream is = null;
		// 初始化输入流
		try {
			is = file.getInputStream();
			priceList = excelUtils.getPriceListByExcel(providerId, is, isExcel2003);
		} catch (IOException e) {
			throw new HandleException(ErrorCode.ARG_ERROR, "打开excel文件异常");
		} finally {
			try {
				if (null != is) {
					is.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 批量导入。参数：文件名，文件。
//		List<PriceBo> priceList = excelUtils.getExcelInfo(providerId, name, file);

		List<PriceBo> failedPriceList = priceService.uploadByExcel(priceList);

		String failedFileName = "uploadFailed.xlsx";
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(failedFileName, "utf-8"));
		ServletOutputStream outputStream = response.getOutputStream();
		exportExcel(failedPriceList, outputStream);

	}

	private void exportExcel(List<PriceBo> list, ServletOutputStream outputStream) {
		// 创建一个workbook 对应一个excel应用文件
		XSSFWorkbook workBook = new XSSFWorkbook();
		// 在workbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = workBook.createSheet("上传失败的价格");
		ExcelExportUtil exportUtil = new ExcelExportUtil(workBook, sheet);
		XSSFCellStyle headStyle = exportUtil.getHeadStyle();
		XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
		// 构建表头
		XSSFRow headRow = sheet.createRow(0);
		String[] titles = { "酒店名", "团队价格", "散客价格", "是否含早", "加早价格", "是否加床", "加床价格", "起始时间", "结束时间", "备注" };
		XSSFCell cell = null;
		for (int i = 0; i < titles.length; i++) {
			cell = headRow.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(titles[i]);
		}
		// 构建表体数据
		if (list != null && list.size() > 0) {
			for (int j = 0; j < list.size(); j++) {
				XSSFRow bodyRow = sheet.createRow(j + 1);
				PriceBo info = list.get(j);

				cell = bodyRow.createCell(0);
				cell.setCellStyle(bodyStyle);
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(info.getHotel().getName());

				cell = bodyRow.createCell(1);
				cell.setCellStyle(bodyStyle);
				cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
				cell.setCellValue(info.getTeamPrice());

				cell = bodyRow.createCell(2);
				cell.setCellStyle(bodyStyle);
				cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
				cell.setCellValue(info.getScatteredPrice());

				cell = bodyRow.createCell(3);
				cell.setCellStyle(bodyStyle);
				cell.setCellType(XSSFCell.CELL_TYPE_BOOLEAN);
				cell.setCellValue(info.getHaveBreakfast());

				cell = bodyRow.createCell(4);
				cell.setCellStyle(bodyStyle);
				cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
				cell.setCellValue(info.getBreakfastPrice());

				cell = bodyRow.createCell(5);
				cell.setCellStyle(bodyStyle);
				cell.setCellType(XSSFCell.CELL_TYPE_BOOLEAN);
				cell.setCellValue(info.getCanAddBed());

				cell = bodyRow.createCell(6);
				cell.setCellStyle(bodyStyle);
				cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
				cell.setCellValue(info.getAddBedPrice());

				cell = bodyRow.createCell(7);
				cell.setCellStyle(bodyStyle);
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(DateUtils.formatDate(info.getStartDate()));

				cell = bodyRow.createCell(8);
				cell.setCellStyle(bodyStyle);
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(DateUtils.formatDate(info.getEndDate()));

				cell = bodyRow.createCell(9);
				cell.setCellStyle(bodyStyle);
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(info.getRemarks());
			}
		}
		try {
			workBook.write(outputStream);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
