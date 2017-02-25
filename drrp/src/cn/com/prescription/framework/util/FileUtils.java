/*
 * Copyright(c) 2011 
 */
package cn.com.prescription.framework.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import cn.com.prescription.framework.StandardConstantsIF;
import cn.com.prescription.framework.exception.SystemException;
import cn.com.prescription.framework.message.MessageUtils;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * ファイル操作用ユーティリティ
 * 
 * @author sk
 */
public class FileUtils {

    /**
     * Excelファイルを出力する。
     * 
     * @author PengShiming
     * @param httpServletResponse
     * @param _wb
     * @param _fileName CSVファイル名
     * @param _realPath パス
     * @throws SystemException
     */
    public static void writeExelFile(XSSFWorkbook _wb, String _fileName, String _realPath) throws SystemException {

        OutputStream outStream_ = null;
        try {
            // 出力先ファイル
            File destFile_ = mkdirsParent(ServiceUtils.getAbsolutePath(getAbsolutePath(_realPath, _fileName)));

            // 出力ストリーム
            outStream_ = new FileOutputStream(destFile_);

            _wb.write(outStream_);

            // ストリームを close
            if (outStream_ != null) {
                outStream_.close();
            }
        } catch (IOException e) {
            // 系统例外
            throw new SystemException(e);
        } finally {
            try {
                if (outStream_ != null) {
                    outStream_.close();
                }
            } catch (IOException e) {
                // 系统例外
                e.printStackTrace();
            }
        }

    }

    /**
     * ファイルをテンポラリフォルダに出力する
     * 
     * @param _bytes byte配列
     * @param _fileName ファイル名
     * @param _realPath パス
     * @throws SystemException 例外が発生する場合、
     */
    public static final void doUpload(byte[] _bytes, String _fileName, String _realPath) throws SystemException {
        write(_bytes, getAbsolutePath(_realPath, _fileName));
    }

    /**
     * Byte配列をファイルに出力
     * 
     * @param _srcBytes byte配列
     * @param _writeFile ファイル
     * @throws SystemException 例外が発生する場合
     */
    public static final void write(byte[] _srcBytes, String _writeFile) throws SystemException {

        OutputStream outStream_ = null;
        try {
            // 出力先ファイル
            File destFile_ = mkdirsParent(ServiceUtils.getAbsolutePath(_writeFile));

            // 出力ストリーム
            outStream_ = new FileOutputStream(destFile_);

            // 書込み
            outStream_.write(_srcBytes);

            // ストリームを close
            if (outStream_ != null) {
                outStream_.close();
            }
        } catch (IOException e) {
            // 系统例外
            throw new SystemException(e);
        } finally {
            try {
                if (outStream_ != null) {
                    outStream_.close();
                }
            } catch (IOException e) {
                // 系统例外
                e.printStackTrace();
            }
        }

    }

    /**
     * 文字配列をファイルに出力
     * 
     * @param _srcStr 文字配列
     * @param _realPath 出力パース
     * @param _encoding エンコード
     * @throws SystemException 例外が発生する場合
     */
    public static final void write(String _srcStr, String _realPath, String _encoding) throws SystemException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        OutputStream outStream_ = null;
        try {

            LinkedList<String> line = new LinkedList<String>();
            line.add(_srcStr);

            try {
                IOUtils.writeLines(line, "\r\n", out, _encoding);
            } catch (IOException e) {
                throw new SystemException("ファイル内容のバイト配列取得でストリームがクローズできません。", e);
            }

            // 出力先ファイル
            File destFile_ = mkdirsParent(ServiceUtils.getAbsolutePath(_realPath));

            // 出力ストリーム
            outStream_ = new FileOutputStream(destFile_);

            // 書込み
            outStream_.write(out.toByteArray());

            // ストリームを close
            if (outStream_ != null) {
                outStream_.close();
            }
        } catch (IOException e) {
            // 系统例外
            throw new SystemException(e);
        } finally {
            try {
                out.close();
                if (outStream_ != null) {
                    outStream_.close();
                }
            } catch (IOException e) {
                // log
                e.printStackTrace();
            }
        }

    }

    // /**
    // * 単一ファイルの上传処理。
    // *
    // * @param _file ファイル
    // * @param _oldFileName ファイル名
    // * @param _newFileName ファイル名
    // * @param _realPath パス
    // * @return 返回结果
    // * @throws Exception 系统例外发生的情况にスローされる
    // */
    // public static final String doUpload(List<File> _file, List<String> _oldFileName, String _newFileName,
    // String _realPath) throws Exception {
    // String fileName = "default.gif";
    //
    // if (_file != null && !_file.isEmpty()) {
    //
    // fileName = _file.get(0).getName();
    // if (!CheckUtils.isEmpty(_newFileName)) {
    // String[] fileNames = _oldFileName.get(0).split("\\.");
    // fileName = _newFileName + "." + fileNames[1];
    // }
    //
    // File destFile = new File(_realPath, fileName);
    //
    // InputStream inputStream = new FileInputStream(_file.get(0));
    // OutputStream outputStream = new FileOutputStream(destFile);
    // byte[] buffer = new byte[400];
    //
    // int length = 0;
    //
    // while ((length = inputStream.read(buffer)) > 0) {
    // outputStream.write(buffer, 0, length);
    // }
    // inputStream.close();
    // outputStream.close();
    // }
    // return fileName;
    // }

    /**
     * 単一ファイルCopy処理.
     * 
     * @param _srcPath コピー元ファイル.
     * @param _dstPath コピー先ファイル.
     * @return コピー先ファイルの絶対パス.
     * @throws SystemException 系统例外发生的情况にスローされる
     */
    public static final String copy(String _srcPath, String _dstPath) throws SystemException {
        // ファイルチャネル
        FileChannel srcChannel_ = null;
        FileChannel dstChannel_ = null;
        try {

            // 絶対パスへ変換
            _srcPath = getAbsolutePath(_srcPath);
            _dstPath = getAbsolutePath(_dstPath);

            // Copy先フォルダが存在しない場合は作成する
            mkdirsParent(_dstPath);
            String fileType = "";
            if (_srcPath.lastIndexOf(".") > 0) {
                fileType = _srcPath.substring(_srcPath.lastIndexOf(".")).toLowerCase();
            }
            if (CheckUtils.isEqual("gif", fileType)) {

                BufferedImage bufferedImage = ImageIO.read(new File(_srcPath));
                Thumbnails.of(new File(_srcPath)).width(bufferedImage.getWidth()).toFile(new File(_dstPath));

            } else {

                // ファイルチャネルを取得
                srcChannel_ = new FileInputStream(_srcPath).getChannel();
                dstChannel_ = new FileOutputStream(_dstPath).getChannel();

                // ファイルCopy
                srcChannel_.transferTo(0, srcChannel_.size(), dstChannel_);
            }

            // 絶対パスを返す
            return _dstPath;

        } catch (Exception e) {
            // 系统例外
            // throw new SystemException(e);
            return e.getMessage();
        } finally {
            // チャネルを閉じる
            closeChannel(srcChannel_);
            closeChannel(dstChannel_);
        }
    }

    /**
     * 単一ファイル移動処理.
     * 
     * @param _srcPath 移動元ファイル.
     * @param _dstPath 移動先ファイル.
     * @return 移動先ファイルの絶対パス.
     * @throws SystemException 系统例外发生的情况にスローされる
     */
    public static final String move(String _srcPath, String _dstPath) throws SystemException {
        // ファイルコピー
        _dstPath = copy(_srcPath, _dstPath);
        // 削除
        new File(_srcPath).delete();
        // ファイル名返却
        return _dstPath;
    }

    /**
     * 単一ファイル削除処理.
     * 
     * @param _path ファイル.
     */
    public static final void delete(String... _path) {
        // 削除
        File tempFile_ = new File(getAbsolutePath(_path));
        if (tempFile_.exists() && tempFile_.isFile()) {
            tempFile_.delete();
        }
    }

    /**
     * フォルダ削除処理.
     * 
     * @param _filePath ファイルパス.
     */
    public static final void deleteFolder(String _filePath) throws SystemException {
        if (CheckUtils.isEmpty(_filePath)) {
            deleteFolder(new File(_filePath));
        }
    }

    /**
     * フォルダ削除処理.
     * 
     * @param _filePath ファイルパス.
     */
    public static final void deleteFolder(File _filePath) throws SystemException {
        if (_filePath != null) {
            if (_filePath.isDirectory()) {

                if (_filePath.list().length == 0) {
                    _filePath.delete();

                } else {

                    String files[] = _filePath.list();

                    for (String temp : files) {
                        File fileDelete = new File(_filePath, temp);

                        deleteFolder(fileDelete);
                    }

                    if (_filePath.list().length == 0) {
                        _filePath.delete();
                    }
                }

            } else {
                _filePath.delete();
            }
        }
    }

    /**
     * 絶対パス取得.
     * 
     * @param _paths パス構成要素
     * @return 絶対パス
     */
    public static String getAbsolutePath(String... _paths) {

        // ファイルパス生成
        File path_ = new File(StringUtils.defaultString(_paths[0]));

        // パス要素を連結
        for (int i = 1; i < _paths.length; ++i) {
            path_ = new File(path_.getPath(), StringUtils.defaultString(_paths[i]));
        }

        // 相対パスの場合は「Context配下」と判断
        if (!path_.isAbsolute()) {
            path_ = new File(ServletActionContext.getServletContext().getRealPath(""), path_.getPath());
        }

        // 絶対パスを返す
        return path_.getAbsolutePath();

    }

    /**
     * コンテキストパス取得.
     * 
     * @param _paths パス構成要素
     * @return コンテキストパス
     */
    public static String getContextPath(String... _paths) {

        // ファイルパス生成
        File path_ = new File(StringUtils.defaultString(_paths[0]));

        // パス要素を連結
        for (int i = 1; i < _paths.length; ++i) {
            path_ = new File(path_.getPath(), StringUtils.defaultString(_paths[i]));
        }

        String resultPath_ = ServletActionContext.getServletContext().getContextPath()
                        + path_.getPath().replace("\\", "/");

        return resultPath_;

    }

    /**
     * 相対パス取得.
     * 
     * @param _paths パス構成要素
     * @return 相対パス
     */
    public static String getRelativePath(String... _paths) {

        // ファイルパス生成
        File path_ = new File(StringUtils.defaultString(_paths[0]));

        // パス要素を連結
        for (int i = 1; i < _paths.length; ++i) {
            path_ = new File(path_.getPath(), StringUtils.defaultString(_paths[i]));
        }

        // 絶対パスを返す
        return path_.getPath();

    }

    /**
     * 一時のPDFファイル名生成.
     * 
     * @return ファイル名 (例：＜現在のセッションID＞ + "_yyyyMMddHHmmssSSS(出力した年月日時分秒ミリ秒).pdf")
     */
    public static String creatPdfFileTempName() {
        // 一時のファイル名生成：セッションID ＋ "_" + 日付フォーマットパターン
        StringBuffer tempFileName = new StringBuffer();
        tempFileName.append(ServiceUtils.getSessionId());
        tempFileName.append("_");
        tempFileName.append(
            DateUtils.format(TimestampUtils.getSysTimestamp(), StandardConstantsIF.DATE_FORMAT_YYYYMMDDHHMMSSSSS));
        tempFileName.append(".pdf");
        // ファイル名のみ返却
        return tempFileName.toString();
    }

    /**
     * CSVファイル名作成.
     * 
     * @param _fileName ファイル名
     * @return ファイル名 (例：＜任意の文字列（PDFの種別がわかる名称）＞ + ".pdf")
     */
    public static String creatPdfFileName(String _fileName) {
        StringBuffer fileName = new StringBuffer();
        fileName.append(MessageUtils.getSimpleMessage(_fileName));
        fileName.append(".pdf");
        // ファイル名のみ返却
        return fileName.toString();
    }

    /**
     * 一時のCSVファイル名生成.
     * 
     * @return ファイル名 (例：＜現在のセッションID＞ + "_yyyyMMddHHmmssSSS(出力した年月日時分秒ミリ秒).csv")
     */
    public static String creatCsvFileTempName() {
        // 一時のファイル名生成：セッションID ＋ "_" + 日付フォーマットパターン
        StringBuffer tempFileName = new StringBuffer();
        tempFileName.append(ServiceUtils.getSessionId());
        tempFileName.append("_");
        tempFileName.append(
            DateUtils.format(TimestampUtils.getSysTimestamp(), StandardConstantsIF.DATE_FORMAT_YYYYMMDDHHMMSSSSS));
        tempFileName.append(".csv");
        // ファイル名のみ返却
        return tempFileName.toString();
    }

    /**
     * CSVファイル名作成.
     * 
     * @param _fileName ファイル名
     * @return ファイル名 (例：＜任意の文字列（CSVの種別がわかる名称）＞ + "_yyyyMMdd_HHmm(出力した年月日時分).csv")
     */
    public static String creatCsvFileName(String _fileName) {
        // 系统日付
        Date sysDate = TimestampUtils.getSysTimestamp();
        StringBuffer fileName = new StringBuffer();
        fileName.append(MessageUtils.getSimpleMessage(_fileName));
        fileName.append("_");
        fileName.append(DateUtils.format(sysDate, StandardConstantsIF.DATE_FORMAT_YYYYMMDD));
        fileName.append("_");
        fileName.append(DateUtils.format(sysDate, StandardConstantsIF.DATE_FORMAT_TIME_HHMM));
        fileName.append(".csv");
        // ファイル名のみ返却
        return fileName.toString();
    }

    /**
     * ファイル名取得.
     * 
     * @param _path ファイルパス
     * @return ファイル名
     */
    public static String getFileName(String _path) {
        // ファイル名のみ返却
        return new File(StringUtils.defaultString(_path)).getName();
    }

    /**
     * 拡張子無しのファイル名を、指定されたファイル名の拡張子で設定して返す
     * 
     * @param _fileName 拡張子的设定ファイル名
     * @param _syncFileName 同期させる拡張子を持ったファイル名
     * @return _fileNameWithoutExt に _syncFileName の拡張子を同期させたファイル名
     * @throws SystemException 予期せぬ例外発生時
     */
    public static String getFileNameSyncExt(String _fileName, String _syncFileName) throws SystemException {

        // 設定先ファイル
        File targetFile_ = new File(StringUtils.defaultString(_fileName));

        // コピー元ファイル名を.での拡張子を抽出
        String[] extWords_ = getFileName(_syncFileName).split("\\.");

        // 拡張子が存在する場合のみ付加
        if (1 < extWords_.length) {
            // 設定する拡張子を抽出
            String ext_ = extWords_[extWords_.length - 1];
            // ファイル名のみ取り出す
            _fileName = targetFile_.getName();
            // 拡張子の位置
            int beginIndex_ = _fileName.lastIndexOf(".");
            // 拡張子を除去
            if (0 <= beginIndex_) {
                _fileName = _fileName.substring(0, beginIndex_);
            }
            // ファイル名を再生成
            targetFile_ = new File(targetFile_.getParent(), _fileName.concat(".").concat(ext_));
        }

        // ファイル名を返す
        return targetFile_.getPath();

    }

    /**
     * テンポラリファイル名を生成し、拡張子をパラメータのファイル名に合わせて返す
     * 
     * @param _syncFileName 同期させる拡張子を持ったファイル名
     * @return テンポラリファイル名（フォルダ無し）
     * @throws SystemException 予期せぬ例外発生時
     */
    public static String getTemporaryFileNameSyncExt(String _syncFileName) throws SystemException {
        // テンポラリファイル名を生成
        String tempFile_ = ServiceUtils.getSessionId().concat("_").concat(ServiceUtils.generateUniqueKey());
        // テンポラリファイル名
        return getFileNameSyncExt(tempFile_, _syncFileName);
    }

    /**
     * ファイル親フォルダ作成.
     * 
     * @param _filePath ファイルパス.
     * @return パラメータで渡されたファイル.
     */
    public static final File mkdirsParent(String _filePath) {
        return mkdirsParent(new File(getAbsolutePath(_filePath)));
    }

    /**
     * ファイル親フォルダ作成.
     * 
     * @param _file ファイル.
     * @return パラメータで渡されたファイルをそのまま返す.
     */
    public static final File mkdirsParent(File _file) {

        // 親フォルダ取得
        File parent_ = _file.getParentFile();

        // 親フォルダ存在チェック
        if (parent_ != null && !parent_.exists()) {
            _file.getParentFile().mkdirs();
        }

        // ファイル返却
        return _file;

    }

    /**
     * ファイル存在チェック
     * 
     * @param _path ファイルパス.
     * @return true:存在する
     */
    public static final Boolean existsFile(String _path) {

        // 親フォルダ取得
        File file_ = new File(getAbsolutePath(_path));

        // ファイル存在チェック
        return file_.exists() && file_.isFile();

    }

    // /**
    // * 複数ファイルの上传処理。
    // *
    // * @param _file ファイル
    // * @param _oldFileName ファイル名
    // * @param _newFileName ファイル名
    // * @param _realPath パス
    // * @throws Exception 系统例外发生的情况にスローされる
    // */
    // /*
    // public static final void doUpload(List<File> _file, List<String> _oldFileName, List<String> _newFileName,
    // String _realPath) throws Exception {
    // if (_file != null && !_file.isEmpty()) {
    // if (_newFileName == null || _newFileName.isEmpty()) {
    // _newFileName = new ArrayList<String>();
    // for (int j = 0; j < _file.size(); j++) {
    // _newFileName.add("");
    // }
    // }
    //
    // for (int i = 0; i < _file.size(); ++i) {
    // if (_file.get(i) != null) {
    // String fileName = _oldFileName.get(i);
    // if (!CheckUtils.isEmpty(_newFileName.get(i))) {
    // String[] fileNames = fileName.split("\\.");
    // fileName = _newFileName.get(i) + "." + fileNames[1];
    // }
    //
    // File destFile = new File(_realPath, fileName);
    //
    // InputStream inputStream = new FileInputStream(_file.get(i));
    // OutputStream outputStream = new FileOutputStream(destFile);
    // byte[] buffer = new byte[400];
    //
    // int length = 0;
    //
    // while ((length = inputStream.read(buffer)) > 0) {
    // outputStream.write(buffer, 0, length);
    // }
    // inputStream.close();
    // outputStream.close();
    // }
    // }
    // }
    // }
    // */

    // /**
    // * テンプルファイル削除処理
    // *
    // * @param _realPath パス
    // * @param _fileName ファイル名
    // * @throws Exception 系统例外发生的情况にスローされる
    // */
    // public static final void doUpdateImg(String _realPath, String _fileName) throws Exception {
    // String[] fileNames = _fileName.split("\\.");
    // String fileName = fileNames[0] + "_confirm" + "." + fileNames[1];
    //
    // File inFile = new File(_realPath, fileName);
    // File destFile = new File(_realPath, _fileName);
    //
    // InputStream inputStream = new FileInputStream(inFile);
    // OutputStream outputStream = new FileOutputStream(destFile);
    // byte[] buffer = new byte[400];
    //
    // int length = 0;
    //
    // while ((length = inputStream.read(buffer)) > 0) {
    // outputStream.write(buffer, 0, length);
    // }
    // inputStream.close();
    // outputStream.close();
    //
    // inFile.delete();
    // }

    /**
     * ストレージファイルアクセス用のキーを取得.
     * 
     * @param _path ストレージファイルパス.
     * @return パス識別キー.
     */
    public static final String saveStoragePath(String _path) {

        // ユニークキーを取得
        String storageKey_ = ServiceUtils.generateUniqueKey();

        // セッションにパス保存
        ServiceUtils.putSession(storageKey_, _path);

        // 生成したキーを返す
        return storageKey_;

    }

    /**
     * ストレージファイルキーからパスを取得.
     * 
     * @param _storageKey {@link #saveStoragePath(String)}で取得したストレージキー.
     * @return 実際のパス.
     */
    public static final String getStoragePath(String _storageKey) {

        // セッションから取得
        return StringUtils.defaultString((String) ServiceUtils.getSession(_storageKey));

    }

    // /**
    // * ファイル削除処理 (doDeleteImgメッソドを削除してdeleteメッソドを統一する)
    // *
    // * @param _realPath パス
    // * @param _fileName ファイル名
    // * @throws Exception 系统例外发生的情况にスローされる
    // */
    // /* public static final void doDeleteImg(String _realPath, String _fileName) throws Exception {
    // File inFile = new File(_realPath, _fileName);
    // inFile.delete();
    // }*/

    /**
     * @param _channel ファイルチャネル
     * @throws SystemException 予期せぬ例外発生時
     */
    private static void closeChannel(FileChannel _channel) throws SystemException {
        try {
            if (_channel != null) {
                _channel.close();
            }
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }

    /**
     * サムネイル用画像を作成する
     * 
     * @param _sourceImageName 画像イメージ元
     * @param _sourceImageName 画像イメージ対象
     * @throws SystemException 予期せぬ例外発生時
     */
    public static void createThumbnail(String _sourceImageName, String _destImageName) throws SystemException {

        try {
            BufferedImage bufferedImage = ImageIO.read(new File(getAbsolutePath(_sourceImageName)));
            int squareHeight = Math.min(bufferedImage.getHeight(), bufferedImage.getWidth());
            int imageWidth = NumberUtils.toInt(StandardConstantsIF.KYOTU_IMAGE_THUMBNAIL_YO_SIZE_CHOHEN);
            int imageHeight = NumberUtils.toInt(StandardConstantsIF.KYOTU_IMAGE_THUMBNAIL_YO_SIZE_TANPEN);
            Thumbnails.of(new File(getAbsolutePath(_sourceImageName)))
                .sourceRegion(Positions.CENTER, squareHeight, squareHeight).size(imageWidth, imageHeight)
                .toFile(new File(getAbsolutePath(_destImageName)));
        } catch (Exception e) {
            e.printStackTrace();
            // throw new SystemException(e);
        }
    }

    /**
     * zipファイル作成用変数
     */
    protected static byte buf[] = new byte[1024];

    /**
     * zipファイルを作成
     * 
     * @param jos
     * @param file 保存先ファイル名
     * @param pathName 作業フォルダパス
     * @param zipFileName ファイル名
     * @throws FileNotFoundException
     * @throws IOException 系统例外
     */
    private static void recurseFiles(ZipOutputStream jos, File file, String pathName, String zipFileName)
                    throws FileNotFoundException, IOException {
        if (file.isDirectory()) {
            pathName = pathName + file.getName() + "/";
            try {
                jos.putNextEntry(new ZipEntry(pathName));
            } catch (IOException e) {
                throw new IOException(e);
            }
            String fileNames[] = file.list();
            if (fileNames != null) {
                // 作業フォルダにファイルをzip圧縮する
                for (int i = 0; i < fileNames.length; i++) {
                    recurseFiles(jos, new File(file, fileNames[i]), pathName, "");
                }
            }
        } else {
            ZipEntry jarEntry = null;
            if (CheckUtils.isEmpty(zipFileName)) {
                jarEntry = new ZipEntry(file.getName());
            } else {
                jarEntry = new ZipEntry(zipFileName);
            }
            if (file.exists() && file.isFile()) {
                FileInputStream fin = null;
                BufferedInputStream in = null;
                try {
                    fin = new FileInputStream(file);
                    in = new BufferedInputStream(fin);
                    jos.putNextEntry(jarEntry);

                    int len = 0;
                    while ((len = in.read(buf)) >= 0) {
                        jos.write(buf, 0, len);
                    }
                } catch (FileNotFoundException e) {
                    throw new FileNotFoundException();
                } catch (IOException e) {
                    throw new IOException(e);
                } finally {

                    try {
                        if (in != null) {
                            in.close();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (fin != null) {
                            fin.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            // ZIP出力ストリームをクローズ
            jos.closeEntry();
        }
    }

    /**
     * zipファイルを作成
     * 
     * @param files 保存先ファイルリスト
     * @param zipFile 保存先ファイル名(必ず完全パスを含む)
     * @throws IOException 系统例外
     */
    public static void toZip(List<File> files, File zipFile) throws IOException, FileNotFoundException {

        ZipOutputStream jos = null;
        try {
            jos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile), 1024));
            for (int i = 0; i < files.size(); i++) {
                recurseFiles(jos, files.get(i), files.get(i).getPath(), "");
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            try {
                if (jos != null) {
                    jos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * zipファイルを作成
     * 
     * @param files 保存先ファイルリスト 例えば：<String, File>ZIP内ファイル名、ファイル
     * @param zipFile 保存先ファイル名(必ず完全パスを含む)
     * @throws IOException 系统例外
     */
    public static void toZip(Map<String, File> files, File zipFile) throws IOException, FileNotFoundException {

        ZipOutputStream jos = null;
        try {
            if (!existsFile(zipFile.getAbsolutePath())) {
                mkdirsParent(zipFile.getAbsolutePath());
            }
            jos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile), 1024));
            for (Map.Entry<String, File> entry : files.entrySet()) {
                recurseFiles(jos, entry.getValue(), entry.getValue().getPath(), entry.getKey());
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            try {
                if (jos != null) {
                    jos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ファイル名から拡張子を返します。
     * 
     * @param _path ファイルパス（例：c\test.csv⇒csv）
     * @return ファイルの拡張子
     */
    public static final String getSuffix(File _path) {

        if (_path.isDirectory()) {
            return null;
        }

        return getSuffix(_path.getName());
    }

    /**
     * ファイル名から拡張子を返します。
     * 
     * @param _path ファイルパス（例：/pub/test/test.csv⇒csv）
     * @return ファイルの拡張子
     */
    public static final String getSuffix(String _path) {

        if (CheckUtils.isEmpty(_path)) {
            return null;
        }

        int lastDotPosition = _path.lastIndexOf(".");
        if (lastDotPosition != -1) {
            return _path.substring(lastDotPosition + 1);
        }
        return null;
    }

    /**
     * フォルダCopy処理.
     * 
     * @param _srcPath コピー元フォルダ.
     * @param _dstPath コピー先フォルダ.
     * @throws SystemException 系统例外发生的情况にスローされる
     */
    public static final void copyFolder(String _srcPath, String _dstPath) throws SystemException {

        if (!CheckUtils.isEmpty(_srcPath) && !CheckUtils.isEmpty(_dstPath)) {
            copyFolder(new File(_srcPath), new File(_dstPath));
        }
    }

    /**
     * フォルダCopy処理.
     * 
     * @param _srcPath コピー元フォルダ.
     * @param _dstPath コピー先フォルダ.
     * @throws SystemException 系统例外发生的情况にスローされる
     */
    public static final void copyFolder(File _srcPath, File _dstPath) throws SystemException {

        if (_srcPath != null && _dstPath != null) {

            if (_srcPath.isDirectory()) {

                if (_dstPath != null && !_dstPath.exists()) {
                    _dstPath.mkdir();
                }

                String files[] = _srcPath.list();

                for (String file : files) {
                    File srcFile = new File(_srcPath, file);
                    File destFile = new File(_dstPath, file);
                    // フォルダCopy処理
                    copyFolder(srcFile, destFile);
                }

            } else {
                // ファイルCopy処理
                copy(_srcPath.getPath(), _dstPath.getPath());
            }
        }

    }

}
