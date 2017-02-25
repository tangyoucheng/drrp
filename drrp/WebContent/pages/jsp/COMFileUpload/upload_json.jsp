<%@ page contentType="text/plain; charset=UTF-8" pageEncoding="UTF-8" %>
{"files":[{
   "name":"${request.uploadResult.name}",
   "size":${request.uploadResult.size},
   "error":"${request.uploadResult.error}",
   "url":"${request.uploadResult.deleteUrl}",
   "thumbnailUrl":"${request.uploadResult.deleteUrl}",
   "deleteUrl":"${request.uploadResult.deleteUrl}",
   "deleteType":"${request.uploadResult.deleteType}"
}]}
