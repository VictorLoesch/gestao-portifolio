<%@ include file="header.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2 class="mb-4">Lista de Projetos</h2>

<c:if test="${not empty sucesso}">
  <div class="alert alert-success" role="alert">
    ${sucesso}
  </div>
</c:if>

<c:if test="${not empty erro}">
  <div class="alert alert-danger" role="alert">
    ${erro}
  </div>
</c:if>

<table class="table table-hover">
  <thead class="table-light">
    <tr>
      <th>ID</th>
      <th>Nome</th>
      <th>Início</th>
      <th>Término</th>
      <th>Status</th>
      <th>Nível Risco</th>
      <th>Ações</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="pr" items="${projetos}">
      <tr>
        <td>${pr.id}</td>
        <td>${pr.nome}</td>
        <td><fmt:formatDate value="${pr.dataInicio}" pattern="dd/MM/yyyy"/></td>
        <td><fmt:formatDate value="${pr.dataFim}" pattern="dd/MM/yyyy"/></td>
        <td>${pr.status}</td>
        <td>${pr.risco}</td>
        <td>
          <a href="${pageContext.request.contextPath}/projetos/editar/${pr.id}" 
             class="btn btn-sm btn-primary">Editar</a>
          <form action="${pageContext.request.contextPath}/projetos/excluir/${pr.id}" 
                method="post" style="display:inline;">
            <button type="submit" class="btn btn-sm btn-danger">Excluir</button>
          </form>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<a href="${pageContext.request.contextPath}/projetos/novo" class="btn btn-success">Novo Projeto</a>

<%@ include file="footer.jspf" %>