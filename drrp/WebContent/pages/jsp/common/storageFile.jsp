<%@page pageEncoding="UTF-8"
%><%@page import="java.io.InputStream"
%><%@page import="java.io.OutputStream"
%><%@page import="java.io.FileInputStream"
%><%@page import="java.io.BufferedInputStream"
%><%@page import="java.io.BufferedOutputStream"
%><%@page import="javax.activation.MimetypesFileTypeMap"
%><%@page import="cn.com.prescription.framework.util.FileUtils"
%><%

	// セッションからパス取得
	String path_ = FileUtils.getStoragePath(request.getParameter("k"));

	// MIMEタイプ
	response.setContentType(new MimetypesFileTypeMap().getContentType(path_));

	// ファイルが存在する場合のみ
	if (FileUtils.existsFile(path_)) {
	
		// 入力ストリーム
		InputStream inStream = new BufferedInputStream(new FileInputStream(FileUtils.getAbsolutePath( path_)));
		// 出力ストリーム
		OutputStream outStream = new BufferedOutputStream(response.getOutputStream());
	
		// 読込バッファ
		byte[] bytes_ = new byte[256];
		// 書込みカウンタ
		int writeCount_ = 0;
	
		// 出力
		while ((writeCount_ = inStream.read(bytes_)) >= 0) {
			outStream.write(bytes_, 0, writeCount_);
		}
	
		// ストリームClose
		inStream.close();
		outStream.close();

	}

%>