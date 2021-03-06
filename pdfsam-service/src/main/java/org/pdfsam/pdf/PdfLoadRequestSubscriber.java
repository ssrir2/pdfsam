/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 13/giu/2013
 * Copyright 2013 by Andrea Vacondio (andrea.vacondio@gmail.com).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pdfsam.pdf;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Named;

import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Component listening for {@link PdfLoadRequestEvent}, triggering the actual pdf load and sending out a response with the result of the loading
 * 
 * @author Andrea Vacondio
 * 
 */
@Named
public class PdfLoadRequestSubscriber {

    private static final Logger LOG = LoggerFactory.getLogger(PdfLoadRequestSubscriber.class);

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Inject
    private PdfLoadService loadService;

    public PdfLoadRequestSubscriber() {
        AnnotationProcessor.process(this);
    }

    /**
     * Request a task execution
     * 
     * @param event
     */
    @EventSubscriber
    public void request(PdfLoadRequestEvent event) {
        LOG.trace("Pdf load request received");
        executor.submit(new LoadTask(event));
    }

    /**
     * Task to load a pdf document
     * 
     * @author Andrea Vacondio
     * 
     */
    private class LoadTask implements Callable<Void> {

        private PdfLoadRequestEvent request;

        public LoadTask(PdfLoadRequestEvent request) {
            this.request = request;
        }

        @Override
        public Void call() {
            List<PdfDocumentDescriptor> loaded = loadService.load(request.getDocuments());
            PdfLoadCompletedEvent response = new PdfLoadCompletedEvent(request.getNamespace());
            response.addAll(loaded);
            EventBus.publish(response);
            return null;
        }

    }
}
