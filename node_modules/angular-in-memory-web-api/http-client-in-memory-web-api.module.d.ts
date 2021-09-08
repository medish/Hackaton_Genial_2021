import { ModuleWithProviders, Type } from '@angular/core';
import { HttpBackend, XhrFactory } from '@angular/common/http';
import { InMemoryBackendConfigArgs, InMemoryBackendConfig, InMemoryDbService } from './interfaces';
import * as ɵngcc0 from '@angular/core';
export declare function httpClientInMemBackendServiceFactory(dbService: InMemoryDbService, options: InMemoryBackendConfig, xhrFactory: XhrFactory): HttpBackend;
export declare class HttpClientInMemoryWebApiModule {
    /**
    *  Redirect the Angular `HttpClient` XHR calls
    *  to in-memory data store that implements `InMemoryDbService`.
    *  with class that implements InMemoryDbService and creates an in-memory database.
    *
    *  Usually imported in the root application module.
    *  Can import in a lazy feature module too, which will shadow modules loaded earlier
    *
    * @param {Type} dbCreator - Class that creates seed data for in-memory database. Must implement InMemoryDbService.
    * @param {InMemoryBackendConfigArgs} [options]
    *
    * @example
    * HttpInMemoryWebApiModule.forRoot(dbCreator);
    * HttpInMemoryWebApiModule.forRoot(dbCreator, {useValue: {delay:600}});
    */
    static forRoot(dbCreator: Type<InMemoryDbService>, options?: InMemoryBackendConfigArgs): ModuleWithProviders<HttpClientInMemoryWebApiModule>;
    /**
   *
   * Enable and configure the in-memory web api in a lazy-loaded feature module.
   * Same as `forRoot`.
   * This is a feel-good method so you can follow the Angular style guide for lazy-loaded modules.
   */
    static forFeature(dbCreator: Type<InMemoryDbService>, options?: InMemoryBackendConfigArgs): ModuleWithProviders<HttpClientInMemoryWebApiModule>;
    static ɵfac: ɵngcc0.ɵɵFactoryDeclaration<HttpClientInMemoryWebApiModule, never>;
    static ɵmod: ɵngcc0.ɵɵNgModuleDeclaration<HttpClientInMemoryWebApiModule, never, never, never>;
    static ɵinj: ɵngcc0.ɵɵInjectorDeclaration<HttpClientInMemoryWebApiModule>;
}

//# sourceMappingURL=http-client-in-memory-web-api.module.d.ts.map