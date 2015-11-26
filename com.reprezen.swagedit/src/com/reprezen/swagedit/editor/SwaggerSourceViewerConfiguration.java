package com.reprezen.swagedit.editor;

import org.dadacoalition.yedit.editor.YEditSourceViewerConfiguration;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.reconciler.IReconciler;
import org.eclipse.jface.text.reconciler.MonoReconciler;
import org.eclipse.jface.text.source.ISourceViewer;

import com.reprezen.swagedit.assist.SwaggerContentAssistProcessor;

public class SwaggerSourceViewerConfiguration extends YEditSourceViewerConfiguration {

	private SwaggerEditor editor;

	public SwaggerSourceViewerConfiguration() {
		super();
	}

	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
		ContentAssistant ca = new ContentAssistant();

		ca.setContentAssistProcessor(new SwaggerContentAssistProcessor(), IDocument.DEFAULT_CONTENT_TYPE);
		ca.setInformationControlCreator(getInformationControlCreator(sourceViewer));

		ca.enableAutoInsert(true);
		ca.enableAutoActivation(true);
		ca.setAutoActivationDelay(100);
		ca.enableColoredLabels(true);

		return ca;
	}

	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] { IDocument.DEFAULT_CONTENT_TYPE };
	}

	@Override
	public IReconciler getReconciler(ISourceViewer sourceViewer) {
		SwaggerReconcilingStrategy strategy = new SwaggerReconcilingStrategy();
		strategy.setEditor(editor);
		MonoReconciler reconciler = new MonoReconciler(strategy, false);
		return reconciler;
	}

	public void setEditor(SwaggerEditor editor) {
		this.editor = editor;
	}

}
