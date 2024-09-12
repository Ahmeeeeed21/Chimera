<?php

namespace App\Controller;

use App\Entity\Commentaire;
use App\Entity\PublicationPhysique;
use App\Form\CommentaireType;
use App\Form\PublicationPhysiqueType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use MercurySeries\FlashyBundle\FlashyNotifier;
/**
 * @Route("/commentaire")
 */
class CommentaireController extends AbstractController
{
    /**
     * @Route("/", name="commentaire_index", methods={"GET"})
     */
    public function index(): Response
    {
        $commentaires = $this->getDoctrine()
            ->getRepository(Commentaire::class)
            ->findAll();

        return $this->render('commentaire/index.html.twig', [
            'commentaires' => $commentaires,
        ]);
    }

    /**
     * @Route("/new/{id}", name="commentaire_new", methods={"GET","POST"})
     */
    public function new(Request $request,$id, FlashyNotifier $flashy): Response
    {$commm=$request->get('cmt');
        $pubphy = $this->getDoctrine()
            ->getRepository(PublicationPhysique::class)
            ->find($id);
        $commentaire = new Commentaire();
        $commentaire->setContenu($commm);
        $commentaire->setDate(date_format(new \DateTime('now'), 'Y-m-d h:m:s'));
        $commentaire->setIdPublication($pubphy);
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
        $entityManager->persist($commentaire);
        $entityManager->flush();

        return $this->redirectToRoute('publication_physique_show',['id'=>$id]);
        /*  return $this->render('commentairepsy/new.html.twig', [
              'commentairepsy' => $commentairepsy,
              'form' => $form->createView(),
          ]);*/
    }

    /**
     * @Route("/{id}", name="commentaire_show", methods={"GET"})
     */
    public function show(Commentaire $commentaire): Response
    {
        return $this->render('commentaire/show.html.twig', [
            'commentaire' => $commentaire,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="commentaire_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Commentaire $commentaire): Response
    {
        $form = $this->createForm(CommentaireType::class, $commentaire);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('commentaire_index');
        }

        return $this->render('commentaire/edit.html.twig', [
            'commentaire' => $commentaire,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="commentaire_deletef", methods={"GET"})
     */
    public function delete(Request $request, Commentaire $commentaire,$id): Response
    {

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($commentaire);
            $entityManager->flush();


        return $this->redirectToRoute('publication_physique_show',['id'=>$id]);
    }
}
