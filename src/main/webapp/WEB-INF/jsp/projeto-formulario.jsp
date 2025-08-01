<%@ include file="header.jspf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2 class="mb-4">
  <c:choose>
    <c:when test="${projeto.id == null}">Novo Projeto</c:when>
    <c:otherwise>Editar Projeto</c:otherwise>
  </c:choose>
</h2>

<form:form action="${pageContext.request.contextPath}/projetos"
           modelAttribute="projeto" method="post" cssClass="needs-validation">
           
 <form:hidden path="id"/> 
  <c:if test="${not empty projeto.id}">
    <input type="hidden" name="id" value="${projeto.id}" />
  </c:if>

  <div class="mb-3">
    <label for="nome" class="form-label">Nome</label>
    <form:input path="nome" id="nome" cssClass="form-control" />
	<form:errors path="nome" cssClass="text-danger"/>           
  </div>

  <div class="mb-3">
    <label for="dataInicio" class="form-label">Data de Início</label>
    <input type="date" id="dataInicio" name="dataInicio"
           value="${projeto.dataInicio}" class="form-control" />
  </div>

  <div class="mb-3">
    <label for="dataPrevisaoFim" class="form-label">Previsão de Término</label>
    <input type="date" id="dataPrevisaoFim" name="dataPrevisaoFim"
           value="${projeto.dataPrevisaoFim}" class="form-control" />
  </div>

  <div class="mb-3">
    <label for="dataFim" class="form-label">Data de Término</label>
    <input type="date" id="dataFim" name="dataFim"
           value="${projeto.dataFim}" class="form-control" />
  </div>

  <div class="mb-3">
    <label for="descricao" class="form-label">Descrição</label>
    <textarea id="descricao" name="descricao"
              class="form-control" rows="4">${projeto.descricao}</textarea>
  </div>

  <div class="mb-3">
    <label for="orcamento" class="form-label">Orçamento (R$)</label>
    <input type="number" step="0.01" id="orcamento" name="orcamento"
           value="${projeto.orcamento}" class="form-control" />
    <form:errors path="orcamento" cssClass="text-danger"/>
  </div>

  <div class="mb-3">
    <label for="status" class="form-label">Status</label>
    <select id="status" name="status" class="form-select">
      <c:forEach var="s" items="${statusList}">
        <option value="${s}"
          <c:if test="${s eq projeto.status}">selected</c:if>>
          ${s}
        </option>
      </c:forEach>
    </select>
  </div>

  <div class="mb-3">
    <label for="risco" class="form-label">Nível de Risco</label>
    <select id="risco" name="risco" class="form-select">
      <c:forEach var="n" items="${nivelRiscoList}">
        <option value="${n}"
          <c:if test="${n eq projeto.risco}">selected</c:if>>
          ${n}
        </option>
      </c:forEach>
    </select>
  </div>

  <div class="mb-3">
    <label for="gerente" class="form-label">Gerente</label>
    <form:select path="gerente.id" id="gerente" cssClass="form-select">
      <form:option value="" label="-- selecione --"/>
      <c:forEach var="g" items="${gerentes}">
        <form:option value="${g.id}" label="${g.nome}"/>
      </c:forEach>
    </form:select>
    <form:errors path="gerente" cssClass="text-danger"/>
  </div>

  <div class="mb-3">
    <label class="form-label">Membros do Projeto</label>
    <c:choose>
      <c:when test="${empty membrosPossiveis}">
        <p class="text-muted">Nenhum funcionário disponível para ser membro.</p>
      </c:when>
      <c:otherwise>
        <div class="table-responsive">
          <table class="table table-sm">
            <thead>
              <tr>
                <th style="width:1%"></th>
                <th>Membros</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="p" items="${membrosPossiveis}">
                <tr>
                  <td>
                    <input type="checkbox"
                           name="membrosId"
                           id="membro_${p.id}"
                           value="${p.id}"
                           <c:if test="${projeto.membros.contains(p)}">checked</c:if> />
                  </td>
                  <td>
                    <label for="membro_${p.id}">${p.nome}</label>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <small class="form-text text-muted">
          Marque os funcionários que farão parte deste projeto.
        </small>
      </c:otherwise>
    </c:choose>
  </div>

  <div class="mt-4">
    <button type="submit" class="btn btn-primary">Salvar</button>
    <a href="${pageContext.request.contextPath}/projetos"
       class="btn btn-secondary">Cancelar</a>
  </div>
</form:form>

<%@ include file="footer.jspf" %>
