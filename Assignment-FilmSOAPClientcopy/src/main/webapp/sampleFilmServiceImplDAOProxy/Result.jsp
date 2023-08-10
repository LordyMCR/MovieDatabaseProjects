<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleFilmServiceImplDAOProxyid" scope="session" class="service.FilmServiceImplDAOProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleFilmServiceImplDAOProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleFilmServiceImplDAOProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleFilmServiceImplDAOProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        service.FilmServiceImplDAO getFilmServiceImplDAO10mtemp = sampleFilmServiceImplDAOProxyid.getFilmServiceImplDAO();
if(getFilmServiceImplDAO10mtemp == null){
%>
<%=getFilmServiceImplDAO10mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
</TABLE>
<%
}
break;
case 15:
        gotMethod = true;
        java.lang.String getFilmsAll15mtemp = sampleFilmServiceImplDAOProxyid.getFilmsAll();
if(getFilmsAll15mtemp == null){
%>
<%=getFilmsAll15mtemp %>
<%
}else{
        String tempResultreturnp16 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getFilmsAll15mtemp));
        %>
        <%= tempResultreturnp16 %>
        <%
}
break;
case 18:
        gotMethod = true;
        String id_1id=  request.getParameter("id21");
        int id_1idTemp  = Integer.parseInt(id_1id);
        java.lang.String getFilmById18mtemp = sampleFilmServiceImplDAOProxyid.getFilmById(id_1idTemp);
if(getFilmById18mtemp == null){
%>
<%=getFilmById18mtemp %>
<%
}else{
        String tempResultreturnp19 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getFilmById18mtemp));
        %>
        <%= tempResultreturnp19 %>
        <%
}
break;
case 23:
        gotMethod = true;
        String stars_3id=  request.getParameter("stars28");
            java.lang.String stars_3idTemp = null;
        if(!stars_3id.equals("")){
         stars_3idTemp  = stars_3id;
        }
        String review_4id=  request.getParameter("review30");
            java.lang.String review_4idTemp = null;
        if(!review_4id.equals("")){
         review_4idTemp  = review_4id;
        }
        String director_5id=  request.getParameter("director32");
            java.lang.String director_5idTemp = null;
        if(!director_5id.equals("")){
         director_5idTemp  = director_5id;
        }
        String year_6id=  request.getParameter("year34");
        int year_6idTemp  = Integer.parseInt(year_6id);
        String title_7id=  request.getParameter("title36");
            java.lang.String title_7idTemp = null;
        if(!title_7id.equals("")){
         title_7idTemp  = title_7id;
        }
        String id_8id=  request.getParameter("id38");
        int id_8idTemp  = Integer.parseInt(id_8id);
        %>
        <jsp:useBean id="model1Film_2id" scope="session" class="model.Film" />
        <%
        model1Film_2id.setStars(stars_3idTemp);
        model1Film_2id.setReview(review_4idTemp);
        model1Film_2id.setDirector(director_5idTemp);
        model1Film_2id.setYear(year_6idTemp);
        model1Film_2id.setTitle(title_7idTemp);
        model1Film_2id.setId(id_8idTemp);
        boolean addFilm23mtemp = sampleFilmServiceImplDAOProxyid.addFilm(model1Film_2id);
        String tempResultreturnp24 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(addFilm23mtemp));
        %>
        <%= tempResultreturnp24 %>
        <%
break;
case 40:
        gotMethod = true;
        String stars_10id=  request.getParameter("stars45");
            java.lang.String stars_10idTemp = null;
        if(!stars_10id.equals("")){
         stars_10idTemp  = stars_10id;
        }
        String review_11id=  request.getParameter("review47");
            java.lang.String review_11idTemp = null;
        if(!review_11id.equals("")){
         review_11idTemp  = review_11id;
        }
        String director_12id=  request.getParameter("director49");
            java.lang.String director_12idTemp = null;
        if(!director_12id.equals("")){
         director_12idTemp  = director_12id;
        }
        String year_13id=  request.getParameter("year51");
        int year_13idTemp  = Integer.parseInt(year_13id);
        String title_14id=  request.getParameter("title53");
            java.lang.String title_14idTemp = null;
        if(!title_14id.equals("")){
         title_14idTemp  = title_14id;
        }
        String id_15id=  request.getParameter("id55");
        int id_15idTemp  = Integer.parseInt(id_15id);
        %>
        <jsp:useBean id="model1Film_9id" scope="session" class="model.Film" />
        <%
        model1Film_9id.setStars(stars_10idTemp);
        model1Film_9id.setReview(review_11idTemp);
        model1Film_9id.setDirector(director_12idTemp);
        model1Film_9id.setYear(year_13idTemp);
        model1Film_9id.setTitle(title_14idTemp);
        model1Film_9id.setId(id_15idTemp);
        boolean editFilm40mtemp = sampleFilmServiceImplDAOProxyid.editFilm(model1Film_9id);
        String tempResultreturnp41 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(editFilm40mtemp));
        %>
        <%= tempResultreturnp41 %>
        <%
break;
case 57:
        gotMethod = true;
        String id_16id=  request.getParameter("id60");
        int id_16idTemp  = Integer.parseInt(id_16id);
        boolean deleteFilm57mtemp = sampleFilmServiceImplDAOProxyid.deleteFilm(id_16idTemp);
        String tempResultreturnp58 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(deleteFilm57mtemp));
        %>
        <%= tempResultreturnp58 %>
        <%
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>