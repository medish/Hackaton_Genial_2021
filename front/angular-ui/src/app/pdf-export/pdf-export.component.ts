import { Component, OnInit } from '@angular/core';
import html2canvas from "html2canvas";
import {jsPDF} from "jspdf";

@Component({
  selector: 'app-pdf-export',
  templateUrl: './pdf-export.component.html',
  styleUrls: ['./pdf-export.component.scss']
})
export class PdfExportComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    function exportToPdf() {
      console.log("start exporting to pdf........");
      var w = document.getElementById("calendar").offsetWidth;
      var h = document.getElementById("calendar").offsetHeight;
      html2canvas(document.getElementById("calendar")).then(function (canvas) {
        var img = canvas.toDataURL("image/calendar");
        var doc = new jsPDF('l', 'px', [w, h]);
        doc.addImage(img, 'JPEG', 0, 0, w, h);
        doc.save("calendrier.pdf");
      })
    }
    document.getElementById("PDFexport").addEventListener("click", exportToPdf);
  }

}
