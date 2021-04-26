<?php

namespace App\Form;

use App\Entity\PublicationPhysique;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class PublicationPhysiqueType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('type',ChoiceType::class,
                array('choices'=>array(
                    'musculation'=>'musculation',
                    'cardio'=>'equilibre',
                    'assouplisement'=>'assouplisement',
                    'equilibre'=>'equilibre',
                ) ))
            ->add('img')
            ->add('description')
            ->add('nom')
            ->add('duree')
            ->add('repetition')

        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => PublicationPhysique::class,
        ]);
    }
}
