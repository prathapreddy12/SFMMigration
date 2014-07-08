<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/jsp/basicFrame/baseLayout.jsp" flush="true">
    <tiles:put name="title" value="Tiles Example" />
    <tiles:put name="header" value="/jsp/basicFrame/header.jsp" />
    <tiles:put name="menu" value="/jsp/basicFrame/menu.jsp" />
    <tiles:put name="body" value="/jsp/basicFrame/body.jsp" />
    <tiles:put name="footer" value="/jsp/basicFrame/footer.jsp" />
</tiles:insert>