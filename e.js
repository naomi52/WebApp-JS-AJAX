var res;

function init()
{
    show("dashDiv");
}

function show(v)
{
    hideAll();
    let n = document.getElementById(v);
    n.style.display = "block";
}

function hideAll()
{
    let list = document.getElementsByClassName("view");
    for (let e of list)
    {
        e.style.display = "none";
    }
}

function prime(f)
{
    let min = f.elements["min"].value;
    let max = f.elements["max"].value;
    let prv = f.elements["prv"].value;
    let opt = f.elements["opt"].value;
   
    let qs = "min=" + min + "&max=" + max + "&prv=" + prv + "&opt=" + opt;
    doSimpleAjax("Prime.do", qs, primeResult);
}


function primeResult(request)
{
   
    if ((request.readyState == 4) && (request.status == 200))
    {
        let resp = JSON.parse(request.responseText);
        document.getElementById("prv").value = resp.result;
        res = resp;

        let html = "";
        if (resp.status == 1)
        {
            html += "<h3 style='color:blue'>The answer is " + resp.result + "</b3>";
        }
        else
        {
            html += "<h3 style='color:red'>Error: " + resp.error + "</h3>";
        }
        document.getElementById("primeResult").innerHTML = html;
    }
   
}

function sis(f)
{
    let pfx = f.elements["prefix"].value;
    let gpa = f.elements["gpa"].value;
    let opt = f.elements["sortBy"].value;
   
    let qs = "pfx=" + pfx + "&gpa=" + gpa + "&opt=" + opt;
    doSimpleAjax("Sis.do", qs, sisResult);
}

function sisResult(request)
{
   
    if ((request.readyState == 4) && (request.status == 200))
    {
        let resp = JSON.parse(request.responseText);

        let html = "";
        if (resp.status == 1)
        {
            html += resp.result;
        }
        else
        {
            html += "<p style='color:red'>Error: " + resp.error + "</p>";
        }
        document.getElementById("sisResult").innerHTML = html;
    }
   
}


function doSimpleAjax(address, data, handler)
{
    var request = new XMLHttpRequest();
    request.onreadystatechange = function() {handler(request);};
    request.open("GET", (address + "?" + data), true);
    request.send(null);
}


//-----------------------------------------------------------------//

function findClicked(){   
   
    document.getElementById("opt").value = "find";
   
}

function nextClicked(){       
   
    document.getElementById("opt").value = "next";
    document.getElementById("min").value = res.result;
   
}

