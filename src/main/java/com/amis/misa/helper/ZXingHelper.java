package com.amis.misa.helper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.amis.misa.dto.EmployeeDto;
import com.amis.misa.entities.app.Employee;
import com.amis.misa.exception.NotFoundException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

public class ZXingHelper {
	public static byte[] getQRCodeImage(EmployeeDto emp, int width, int height) {
		try {
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode("ID: " + emp.getId() + "\n " + "Name: " + emp.getEmployeeName(),
					BarcodeFormat.QR_CODE, width, height);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
			return byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			return null;
		}
	}

	public static String readQRCode(InputStream inputStream) {
		BufferedImage bufferedImage;
		try {
			File file = new File(
					"C:\\Users\\0961011310\\eclipse-workspace\\ManagerEmployee\\src\\main\\resources\\image\\tttt1.PNG");

			bufferedImage = ImageIO.read(new FileInputStream(file));
			BufferedImage cropedImage = bufferedImage.getSubimage(0, 0, 350, 300);
			LuminanceSource source = new BufferedImageLuminanceSource(cropedImage);
			BinaryBitmap bitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));
//			Bitmap resize = Bitmap.createScaledBitmap(srcBitmap,
//			 dstWidth,dstHeight,false);
			Map<DecodeHintType, Object> tmpHintsMap = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);

			// tmpHintsMap.put(DecodeHintType.TRY_HARDER, Boolean.FALSE);
			// tmpHintsMap.put(DecodeHintType.POSSIBLE_FORMATS,
			// EnumSet.allOf(BarcodeFormat.class));
			tmpHintsMap.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
			MultiFormatReader reader = new MultiFormatReader();
			Result result = reader.decode(bitmap, tmpHintsMap);
			return result.getText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());
		} catch (com.google.zxing.NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		} catch (ChecksumException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;

	}
}
