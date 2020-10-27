package com.x.hotels.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.x.hotels.context.ErrorCode;
import com.x.hotels.context.HandleException;
import com.x.hotels.context.bo.PriceBo;
import com.x.hotels.entity.Hotel;
import com.x.hotels.entity.Provider;
import com.x.tools.util.DateUtils;

@Service
public class ExcelPriceService {
	// 总行数
//	private int totalRows = 0;
//	// 总条数
//	private int totalCells = 0;
//	// 错误信息接收器
//	private String errorMsg;

	// 构造方法
//	public ExcelPriceService() {
//	}

	// 获取总行数
//	public int getTotalRows() {
//		return totalRows;
//	}
//
//	// 获取总列数
//	public int getTotalCells() {
//		return totalCells;
//	}
//
//	// 获取错误信息
//	public String getErrorInfo() {
//		return errorMsg;
//	}

	/**
	 * 验证EXCEL文件
	 * 
	 * @param filePath
	 * @return
	 */
//	public boolean validateExcel(String filePath) {
//		if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath))) {
//			// errorMsg = "文件名不是excel格式";
//			return false;
//		}
//		return true;
//	}

	/**
	 * 读EXCEL文件，获取客户信息集合
	 * 
	 * @param fielName
	 * @return
	 * @throws IOException
	 */
//	public List<PriceBo> getExcelInfo(String providerId, String fileName, MultipartFile Mfile) throws IOException {
//
//		// 初始化客户信息的集合
//		List<PriceBo> priceList = new ArrayList<PriceBo>();
//		// 初始化输入流
//		InputStream is = null;
//		try {
//			// 验证文件名是否合格
//			if (!validateExcel(fileName)) {
//				throw new IOException("文件名不是excel格式");
//			}
//			// 根据文件名判断文件是2003版本还是2007版本
//			boolean isExcel2003 = true;
//			if (WDWUtil.isExcel2007(fileName)) {
//				isExcel2003 = false;
//			}
//			// 根据新建的文件实例化输入流
//			is = Mfile.getInputStream();// new FileInputStream(file1);
//			// 根据excel里面的内容读取客户信息
//			priceList = getExcelInfo(providerId, is, isExcel2003);
//		} finally {
//			if (is != null) {
//				try {
//					is.close();
//				} catch (IOException e) {
//					is = null;
//					e.printStackTrace();
//				}
//			}
//		}
//		return priceList;
//	}

	/**
	 * 根据excel里面的内容读取客户信息
	 * 
	 * @param is          输入流
	 * @param isExcel2003 excel是2003还是2007版本
	 * @return
	 * @throws IOException
	 */
	public List<PriceBo> getPriceListByExcel(String providerId, InputStream is, boolean isExcel2003) {
		List<PriceBo> priceList = null;
		/** 根据版本选择创建Workbook的方式 */
		// 当excel是2003时
		if (isExcel2003) {
			// Workbook wb = new HSSFWorkbook(is);
			throw new HandleException(ErrorCode.ARG_ERROR, "仅支持excel2007以上版本");
		} else {// 当excel是2007时
			try {
				XSSFWorkbook wb = new XSSFWorkbook(is);
				priceList = readExcelValue(providerId, wb);
			} catch (Exception e) {
				e.printStackTrace();
				throw new HandleException(ErrorCode.ARG_ERROR, "解析excel失败");
			}
		}
		// 读取Excel里面的信息
		return priceList;
	}

	/**
	 * @param wb
	 * @return
	 * @throws Exception
	 */
	private List<PriceBo> readExcelValue(String providerId, XSSFWorkbook wb) throws Exception {
		int totalRows = 0;
		// 总条数
		int totalCells = 0;
		// 错误信息接收器
		// String errorMsg;

		// 得到第一个shell
		XSSFSheet sheet = wb.getSheetAt(0);

		// 得到Excel的行数
		totalRows = sheet.getPhysicalNumberOfRows();

		// 得到Excel的列数(前提是有行数)
		if (totalRows >= 1 && sheet.getRow(0) != null) {
			totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}

		List<PriceBo> priceList = new ArrayList<PriceBo>();
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			XSSFRow row = sheet.getRow(rowIndex);
			if (row == null)
				continue;

			PriceBo price = new PriceBo();
			XSSFCell hotelNameCell = row.getCell(0); // 酒店名称
			XSSFCell teamPriceCell = row.getCell(1); // 团队价格
			XSSFCell scatteredPriceCell = row.getCell(2); // 散客价格
			XSSFCell haveBreakfastCell = row.getCell(3);// 是否含早
			XSSFCell breakfastPriceCell = row.getCell(4); // 加早餐价格
			XSSFCell canAddBedCell = row.getCell(5); // 是否加床
			XSSFCell addBedPriceCell = row.getCell(6); // 加床价格
			XSSFCell startDateCell = row.getCell(7); // 开始时间
			XSSFCell endDateCell = row.getCell(8); // 结束时间
			XSSFCell remarksCell = row.getCell(9); // 备注

			String hotelName = hotelNameCell.getStringCellValue();
			if (hotelName != null) {
				hotelName = hotelName.trim();
			}
			if (hotelName.isEmpty()) {
				totalRows = rowIndex - 1;
				break;
			}
			Hotel hotel = new Hotel();
			hotel.setName(hotelName);
			price.setHotel(hotel);

			Provider provider = new Provider();
			provider.setId(providerId);
			price.setProvider(provider);
			
			if (teamPriceCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
				String teamPrice = teamPriceCell.getStringCellValue();
				if (teamPrice != null) {
					teamPrice = teamPrice.trim();
				}
				price.setTeamPrice(new Integer(teamPrice));
			} else if (teamPriceCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				double teamPrice = teamPriceCell.getNumericCellValue();
				price.setTeamPrice(Integer.valueOf((int) teamPrice));
			}

			if (scatteredPriceCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
				String scatteredPrice = scatteredPriceCell.getStringCellValue();
				if (scatteredPrice != null) {
					scatteredPrice = scatteredPrice.trim();
				}
				price.setTeamPrice(new Integer(scatteredPrice));
			} else if (scatteredPriceCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				double scatteredPrice = scatteredPriceCell.getNumericCellValue();
				price.setScatteredPrice(Integer.valueOf((int) scatteredPrice));
			}

			if (breakfastPriceCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
				String breakfastPrice = breakfastPriceCell.getStringCellValue();
				if (breakfastPrice != null) {
					breakfastPrice = breakfastPrice.trim();
				}
				price.setBreakfastPrice(new Integer(breakfastPrice));
			} else if (breakfastPriceCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				double breakfastPrice = breakfastPriceCell.getNumericCellValue();
				price.setBreakfastPrice(Integer.valueOf((int) breakfastPrice));
			}

			if (canAddBedCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
				String canAddBed = canAddBedCell.getStringCellValue();
				if ("是".equals(canAddBed)) {
					price.setCanAddBed(true);
				} else {
					price.setCanAddBed(false);
				}
			} else if (canAddBedCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
				price.setCanAddBed(canAddBedCell.getBooleanCellValue());
			}

			if (haveBreakfastCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
				String haveBreakfast = haveBreakfastCell.getStringCellValue();
				if ("是".equals(haveBreakfast)) {
					price.setHaveBreakfast(true);
				} else {
					price.setHaveBreakfast(false);
				}
			} else if (haveBreakfastCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
				price.setHaveBreakfast(haveBreakfastCell.getBooleanCellValue());
			}

			if (addBedPriceCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
				String addBedPrice = addBedPriceCell.getStringCellValue();
				if (addBedPrice != null) {
					addBedPrice = addBedPrice.trim();
				}
				price.setAddBedPrice(new Integer(addBedPrice));
			} else if (addBedPriceCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				double addBedPrice = addBedPriceCell.getNumericCellValue();
				price.setAddBedPrice(Integer.valueOf((int) addBedPrice));
			}

			if (startDateCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
				String startDateStr = startDateCell.getStringCellValue();
				Date startDate = DateUtils.parseDate(startDateStr);
				price.setStartDate(startDate);
			} else if (startDateCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				price.setStartDate(startDateCell.getDateCellValue());
			}

			if (endDateCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
				String endDateStr = endDateCell.getStringCellValue();
				Date endDate = DateUtils.parseDate(endDateStr);
				price.setEndDate(endDate);
			} else if (endDateCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				price.setEndDate(endDateCell.getDateCellValue());
			}

			//remarksCell可能是空的
			if (remarksCell != null){
				String remarks = remarksCell.getStringCellValue();
				if (remarks != null) {
					remarks = remarks.trim();
				}
				price.setRemarks(remarks);
			}
			priceList.add(price);
		}
		return priceList;
	}
}
