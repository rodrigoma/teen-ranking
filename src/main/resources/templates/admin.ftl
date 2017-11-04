<!DOCTYPE html>

<html lang="en">
<head><title> IEAB SCS - ADMIN Ranking Adolescentes </title>

    <style>
        input[alt=pontos]{
            width: 50px;
        }
        body, input {
            font-family: Calibri, Arial;
            margin: 0px;
            padding: 0px;
        }
        #header h2 {
            color: white;
            background-color: #3275A8;
            height: 50px;
            padding: 5px 0 0 5px;
            font-size: 20px;
        }

        .datatable {margin-bottom:5px;border:1px solid #eee;border-collapse:collapse;width:500px;max-width:100%;font-family:Calibri}
        .datatable th {padding:3px;border:1px solid #888;height:30px;background-color:#B2D487;text-align:center;vertical-align:middle;color:#444444}
        .datatable tr {border:1px solid #888}
        .datatable tr.odd {background-color:#eee}
        .datatable td {padding:2px;border:1px solid #888}
        #content { padding 5px; margin: 5px; text-align: center}
        fieldset { width: 300px; padding: 5px; margin-bottom: 0px; }
        legend { font-weight: bold; }
    </style>

<body>

<div id="header">
    <h2>ADMIN Ranking Adolescentes</h2>
</div>
<div id="content">
    <fieldset>
        <legend>Adicionar Adolescente</legend>
        <form name="teen" action="/admin/create" method="post">
            Nome  : <input type="text" name="name" /><br/>
            Pontos: <input type="number" value="0" name="points" /><br/>
            <input type="submit" value="Salvar" />
        </form>
    </fieldset>
    <br/>
    <table class="datatable">
        <tr>
            <th>Nome</th>
            <th>Pontos</th>
            <th></th>
            <th></th>
        </tr>
    <#list teenList as teen>
        <tr>
            <td>${teen.name}</td>
            <td>${teen.points}</td>
            <td>
                <form name="teen" action="/admin/incrise" method="post">
                    <input type="hidden" name="name" value="${teen.name}"/>
                    <input type="number" name="points" alt="pontos" value="0" />
                    <input type="submit" value="+/-" />
                </form>
            </td>
            <td>
                <a href="/admin/delete/${teen.hash}">
                <img src="delete.jpg" width="10" height="10">
                </a>
            </td>
        </tr>
    </#list>
    </table>
</div>

</body>
</html>