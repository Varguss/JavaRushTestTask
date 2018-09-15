<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Компоненты</title>

    <link href="<c:url value="/resources/style/style.css"/>" rel="stylesheet" />
</head>
<body>
    <div class="content-panel">
        <div class="content-header">
            Управление складом компьютерных запчастей
        </div>

        <c:if test="${not empty searchResults}">
        <div class="search-content">
            <div class="search-name">
                Результаты поиска по "${param.partOfName}":
            </div>

            <div class="search-results">
                <table class="result-table">
                    <tr>
                        <th class="th-cell">ID</th><th class="th-cell">Название</th><th class="th-cell">Количество</th><th class="th-cell">Важная?</th>
                    </tr>

                    <c:forEach items="${searchResults}" var="detail">
                        <tr><td class="cell">${detail.id}</td><td class="cell">${detail.name}</td><td class="cell">${detail.count}</td><td class="cell">${detail.important == true ? 'Важная' : 'Дополнительная'}</td></tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        </c:if>

        <div class="details">
            <div class="search-name">
                Записи на странице №${lastPage.get()}:
            </div>

            <table class="result-table">
                <tr>
                    <th class="th-cell">ID</th><th class="th-cell">Название</th><th class="th-cell">Количество</th><th class="th-cell">Важная?</th>
                </tr>

                <c:forEach items="${details}" var="detail">
                    <tr><td class="cell">${detail.id}</td><td class="cell">${detail.name}</td><td class="cell">${detail.count}</td><td class="cell">${detail.important == true ? 'Важная' : 'Дополнительная'}</td></tr>
                </c:forEach>
            </table>

            <h2>Всего можно собрать компьютеров: ${totalCollectedComputers}</h2>
        </div>
    </div>

    <div class="control-panel">
        <h2 class="control-header">Панель управления</h2>

        <div class="filter-buttons-container">
            <a class="filter-button-wrapper" href="<c:url value="/filter?filter=none"/>">
                <c:choose><c:when test="${strategy.equals('AllStrategy')}"><span class="filter-button-activated">Все</span></c:when><c:otherwise><span class="filter-button">Все</span></c:otherwise></c:choose>
            </a>

            <a class="filter-button-wrapper" href="<c:url value="/filter?filter=important"/>">
                <c:choose><c:when test="${strategy.equals('ImportantStrategy')}"><span class="filter-button-activated">Важные</span></c:when><c:otherwise><span class="filter-button">Важные</span></c:otherwise></c:choose>
            </a>

            <a class="filter-button-wrapper" href="<c:url value="/filter?filter=optional"/>">
                <c:choose><c:when test="${strategy.equals('OptionalStrategy')}"><span class="filter-button-activated">Дополнительные</span></c:when><c:otherwise><span class="filter-button">Дополнительные</span></c:otherwise></c:choose>
            </a>
        </div>

        <div class="form-container">
            <div class="form-header">Добавление/изменение запчастей</div>

        <form:form cssClass="form" action="/savePart" method="post" modelAttribute="computerPart">
            <table>
                <tr>
                    <td>
                        <form:label cssClass="label" path="id">ID существующей запчасти (опционально):</form:label>
                    </td>
                    <td>
                        <form:input class="input" path="id"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <form:label cssClass="label" path="name">Название запчастей:</form:label>
                    </td>
                    <td>
                        <form:input class="input" path="name" required="required" />
                    </td>
                </tr>

                <tr>
                    <td>
                        <form:label cssClass="label" path="count">Количество запчастей: </form:label>
                    </td>
                    <td>
                        <form:input class="input" path="count" type="number" required="required" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label cssClass="label" path="important">Обязательные?</form:label>
                </td>
                    <td>
                        <form:checkbox path="important" />
                    </td>
                </tr>

                <tr><td colspan="2"><input class="submit" type="submit" value="Добавить!"/></td></tr>
            </table>
        </form:form>
        </div>

        <div class="form-container">
            <div class="form-header">Поиск запчастей</div>

            <form class="form" action="<c:url value="/search"/>">
                <table>
                    <tr>
                        <td>
                            <label class="label" for="searchInputField">Поиск по имени: </label>
                        </td>
                        <td>
                            <input class="input" id="searchInputField" type="text" required maxlength="255" name="partOfName"/>
                        </td>
                    </tr>

                    <tr><td colspan="2"><input class="submit" type="submit" value="Найти!"/></td></tr>
                </table>
            </form>
        </div>

        <div class="form-container">
            <div class="form-header">Удаление запчастей</div>

        <form class="form" action="<c:url value="/removePart"/>" method="post">
            <table>
                <tr>
                    <td>
                        <label for="partIdRemove" class="label">ID запчасти: </label>
                    </td>
                    <td>
                        <input class="input" type="number" name="partId" id="partIdRemove" required />
                    </td>
                </tr>

                <tr><td colspan="2"><input class="submit" type="submit" value="Удалить!"/></td></tr>
            </table>
        </form>
        </div>

        <div class="filter-buttons-container">
            <c:forEach end="${lastPageNumber}" step="1" begin="1" var="index">
                <a class="filter-button-wrapper" href="/show/${index}">
                    <span class="filter-button">${index}</span>
                </a>
            </c:forEach>
        </div>
    </div>
</body>
</html>