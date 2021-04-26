<?php

namespace App\Controller;

use App\Entity\Commentairepsy;
use App\Entity\PublicationPsy;
use App\Form\CommentairepsyType;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/commentairepsy")
 */
class CommentairepsyController extends AbstractController
{
    /**
     * @Route("/", name="commentairepsy_index", methods={"GET"})
     */
    public function index(): Response
    {
        $commentairepsies = $this->getDoctrine()
            ->getRepository(Commentairepsy::class)
            ->findAll();

        return $this->render('commentairepsy/index.html.twig', [
            'commentairepsies' => $commentairepsies,
        ]);
    }

    /**
     * @Route("/new/{id}", name="commentairepsy_new", methods={"GET","POST"})
     */
    public function new(Request $request,$id, FlashyNotifier $flashy): Response
    {$commm=$request->get('cmt');
        $pubpsy = $this->getDoctrine()
            ->getRepository(PublicationPsy::class)
            ->find($id);
        $commentairepsy = new Commentairepsy();
        $commentairepsy->setDate(date_format(new \DateTime('now'), 'Y-m-d h:m:s'));
        $commentairepsy->setContenu($commm);
        $commentairepsy->setIdPublication2($pubpsy);
        $flashy->success('Votre Commentaire a été ajouté');
      /*  $form = $this->createForm(CommentairepsyType::class, $commentairepsy);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($commentairepsy);
            $entityManager->flush();

            return $this->redirectToRoute('commentairepsy_index');
        }*/
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($commentairepsy);
        $entityManager->flush();

        return $this->redirectToRoute('publication_psy_show',['id'=>$id]);
      /*  return $this->render('commentairepsy/new.html.twig', [
            'commentairepsy' => $commentairepsy,
            'form' => $form->createView(),
        ]);*/
    }

    /**
     * @Route("/{id}", name="commentairepsy_show", methods={"GET"})
     */
    public function show(Commentairepsy $commentairepsy): Response
    {
        return $this->render('commentairepsy/show.html.twig', [
            'commentairepsy' => $commentairepsy,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="commentairepsy_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Commentairepsy $commentairepsy): Response
    {
        $form = $this->createForm(CommentairepsyType::class, $commentairepsy);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('commentairepsy_index');
        }

        return $this->render('commentairepsy/edit.html.twig', [
            'commentairepsy' => $commentairepsy,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="commentairepsy_deletef", methods={"POST"})
     */
    public function delete(Request $request, Commentairepsy $commentairepsy): Response
    {
        if ($this->isCsrfTokenValid('delete'.$commentairepsy->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($commentairepsy);
            $entityManager->flush();
        }

        return $this->redirectToRoute('commentairepsy_index');
    }
}
