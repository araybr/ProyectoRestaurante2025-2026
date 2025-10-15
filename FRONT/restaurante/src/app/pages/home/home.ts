import { Component, AfterViewInit, ViewEncapsulation } from '@angular/core';

declare var $: any;

@Component({
  selector: 'app-home',
  templateUrl: './home.html',
  styleUrls: ['./home.css'],
  encapsulation: ViewEncapsulation.None // <-- esto hace que los CSS sean globales
})

export class Home implements AfterViewInit  {
  ngAfterViewInit(): void {
    this.loadScript('assets/js/modernizer.js')
      .then(() => this.loadScript('assets/js/bootstrap.min.js'))
      .then(() => this.loadScript('assets/js/all.js'))
      .then(() => this.loadScript('assets/js/custom.js'))
      .then(() => {
        // Inicializar Owl Carousel
        ($('#owl-demo') as any).owlCarousel({
          autoPlay: 3000,
          items: 3,
          itemsDesktop: [1199, 3],
          itemsDesktopSmall: [979, 2]
        });
      })
      .catch(err => console.error(err));
  }

  private loadScript(url: string): Promise<void> {
    return new Promise((resolve, reject) => {
      const script = document.createElement('script');
      script.src = url;
      script.async = true;
      script.onload = () => resolve();
      script.onerror = () => reject(`No se pudo cargar ${url}`);
      document.body.appendChild(script);
    });
  }
}
